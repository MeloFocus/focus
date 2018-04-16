package com.melo.focus.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melo.focus.domain.basic.Resource;
import com.melo.focus.domain.basic.User;
import com.melo.focus.domain.vm.ResourceSaveVM;
import com.melo.focus.domain.vm.ResourceUpdateVM;
import com.melo.focus.domain.vm.ResourceVM;
import com.melo.focus.mapper.basic.ResourceMapper;
import com.melo.focus.mapper.extend.ResourceExtendMapper;
import com.melo.focus.util.Asserts;
import com.melo.focus.util.Constants;
import com.melo.focus.util.EntityUtils;

@Service
@Transactional
public class ResourceService {
	
	@Autowired
	ResourceMapper  resourceMapper;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	RoleResourceService roleResourceService; 
	
	@Autowired
	ResourceExtendMapper resoourceExtendMapper;

	@Autowired
	ResourceAuthorityService resourceAuthorityService;
	
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	RedissonClient redissonClient;
	
	public void addResource(ResourceSaveVM resourceSaveVM) {

		Asserts.validate(resourceSaveVM, "resourceSaveVM");
		
		Resource resource = EntityUtils.vm2Entity(resourceSaveVM, Resource.class);
		resource.setId(UUID.randomUUID().toString());
		
		//根据参数父节点code 查出父节点id set，并组合子节点path
		Resource parentResource = resoourceExtendMapper.getIdByCode(resourceSaveVM.getParentCode());
		if(parentResource!=null){
			resource.setPid(parentResource.getId());
			resource.setPath(parentResource.getPath()+"/"+resource.getCode());
		}else{
			resource.setPid("pid");
			resource.setPath("/"+resource.getCode());
		}
		
		
		resourceMapper.insert(resource);
		
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		String userId=user.getId();
		deleteRedis(userId);
	}

	public void updateResource(ResourceUpdateVM resourceUpdateVM) {
		Asserts.validate(resourceUpdateVM,"resourceUpdateVM");
		
		Resource resource = EntityUtils.vm2Entity(resourceUpdateVM, Resource.class);
		
		//根据参数父节点code 查出父节点id set，并组合子节点path
		Resource parentResource = resoourceExtendMapper.getIdByCode(resourceUpdateVM.getParentCode());
		resource.setPid(parentResource.getId());
		
		Resource lastResource = resourceMapper.selectByPrimaryKey(resource.getId());
		String lastCode=lastResource.getCode();
		String nextCode=resource.getCode();
		//如果code 修改了，下级的path要改
		if(!lastCode.equals(nextCode)){
			//根据code 查出  此节点资源  及以下的 所有资源
			List<Resource> resourceList = resoourceExtendMapper.selectByCode(lastCode);
			for(Resource r:resourceList){
				r.setPath(r.getPath().replaceAll(lastCode, nextCode));
				resourceMapper.updateByPrimaryKey(r);
			}
		}else{
			resourceMapper.updateByPrimaryKey(resource);
		}
		
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		String userId=user.getId();
		deleteRedis(userId);
	}

	public void deleteResource(String resourceId) {
		Asserts.notEmpty(resourceId);
		
		//先删资源对应的权限
		resourceAuthorityService.deleteResourceAuthority(resourceId,null);
		//删除角色资源关系
		roleResourceService.deleteRoleResource(null, resourceId);
		//根据code path删除本身及下级资源
		Resource resource = resourceMapper.selectByPrimaryKey(resourceId);
		resoourceExtendMapper.deleteByPath(resource.getCode());
		
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		String userId=user.getId();
		deleteRedis(userId);
	}

	/**
	 * 得到资源列表
	 * @return
	 */
	public List<ResourceVM> getAllResource() {
		List<Resource> all = resourceMapper.selectAll();
		return parseTree(all);
	}

	/**
	 * 将资源集合封装为树
	 */
	private List<ResourceVM>parseTree(List<Resource>resourceList){
		
		List<ResourceVM> list = EntityUtils.entity2VMList(resourceList,ResourceVM.class);
		Map <String,ResourceVM>map=new HashMap<String,ResourceVM>();
		List<ResourceVM>root=new ArrayList<ResourceVM>();
		for(ResourceVM resouceVM:list){
			resouceVM.setChildren(new ArrayList<ResourceVM>());
			map.put(resouceVM.getId(),resouceVM);
		}
		
		for(ResourceVM resourceVM:list){
			ResourceVM parentResource = map.get(resourceVM.getPid());
			if(parentResource==null){
				root.add(resourceVM);
				continue;
			}
			parentResource.getChildren().add(resourceVM);
		}
		return root;
	}
	
	
	/**
	 * 得到当前用户资源树
	 * @return
	 */
	public List<ResourceVM> getCurrentUserResourceTree() {
		
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		String userId=user.getId();
		
		//先从缓存中拿，如果 前端有多个模块，根据code 查询资源树更合适
		//RmapCache<userID,HashMap<resource.code,resourceTree>>这个结构,可以很快拿到每个code节点下的 树
		
		RMapCache<String,List<ResourceVM>> mapCache = redissonClient.getMapCache(Constants.REDIS_RESOURCE_TREE);//每个userid对应的资源树
		RMapCache<String, List<Resource>> mapCache2 = redissonClient.getMapCache(Constants.REDIS_RESOURCE);//每个userid对应的无序资源集
		if(mapCache.get(userId)!=null){
			
			return mapCache.get(userId);
			
		}else if(mapCache2.get(userId)!=null){
			List<ResourceVM> parseTree = parseTree(mapCache2.get(userId));
			mapCache.put(userId, parseTree);
			return parseTree;
			
		}else if(user.getSuperman()){
			//超管
			List<ResourceVM> allResource = getAllResource();
			mapCache.put(userId, allResource);
			return allResource;
		}
		else{
			//每个用户会有一个基本的角色，保证能看到一些基本菜单，不会出现 roleIds resourceIds空的情况
			List<Resource>resourceList=getResourceByUserId(userId);
			List<ResourceVM> parseTree = parseTree(resourceList);
			mapCache.put(userId, parseTree);
			return parseTree;
		}
	}

	/**
	 * 根据资源id集合查出资源
	 */
	public List<Resource> selectByResourceIds(List<String> resourceIds){
		if(CollectionUtils.isEmpty(resourceIds)){return null;}
		List<Resource> resourceList = resoourceExtendMapper.selectByResourceIds(resourceIds);
		return resourceList;
	}
	
	/**
	 * 根据 userid查出用户拥有的资源
	 */
	public List<Resource>getResourceByUserId(String userId){
		List<String> roleIds = userRoleService.getRoleByUser(userId);
		List<String> resourceIds = roleResourceService.getResource(roleIds);
		return selectByResourceIds(resourceIds);
	}
	/**
	 * 得到全部资源
	 */
	public List<Resource>getAll(){
		return resourceMapper.selectAll();
	}
	
	/** 
	 * 清空某用户id的资源树缓存，在资源变动时调用
	 */
	public void deleteRedis(String userId){
		RMapCache<String,List<ResourceVM>> mapCache = redissonClient.getMapCache(Constants.REDIS_RESOURCE_TREE);//每个userid对应的资源树
		RMapCache<String, List<Resource>> mapCache2 = redissonClient.getMapCache(Constants.REDIS_RESOURCE);//每个userid对应的无序资源集
		if(mapCache!=null){mapCache.remove(userId);}
		if(mapCache2!=null){mapCache2.remove(userId);}
	}

}
