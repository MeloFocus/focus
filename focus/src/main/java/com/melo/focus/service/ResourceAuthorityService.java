package com.melo.focus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melo.focus.domain.basic.Authority;
import com.melo.focus.domain.basic.Resource;
import com.melo.focus.domain.basic.ResourceAuthority;
import com.melo.focus.mapper.basic.ResourceAuthorityMapper;
import com.melo.focus.mapper.extend.ResourceAuthorityExtendMapper;
import com.melo.focus.util.Asserts;

@Service
@Transactional
public class ResourceAuthorityService {
	
	@Autowired
	ResourceAuthorityExtendMapper resourceAuthorityExtendMapper;
	
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	ResourceAuthorityMapper resourceAuthorityMapper;

	/**
	 * 根据参数资源 查出其拥有的 权限
	 */
	public List<String> getAuthorityByResource(List<String> resourceList){
		if(CollectionUtils.isEmpty(resourceList)){return null;}
		return resourceAuthorityExtendMapper.getAuthorityByResource(resourceList);
	}
	
	/**
	 * 查单个资源拥有的权限
	 * @param resourceId
	 * @return
	 */
	public List<Authority> getAuthorityByResource(String resourceId) {
		List<String>list=new ArrayList<>();
		list.add(resourceId);
		List<String> authorityIds = resourceAuthorityExtendMapper.getAuthorityByResource(list);
		
		return authorityService.getAuthorityByIds(authorityIds);
	}

	/**
	 * 取消授权
	 * @param resourceId
	 * @param authorityId
	 */
	public void deleteResourceAuthority(String resourceId, String authorityId) {
		resourceAuthorityExtendMapper.deleteResourceAuthority(resourceId,authorityId);
	}

	/**
	 * 给资源授权
	 * @param resourceId
	 * @param authorityId
	 */
	public void authorize(String resourceId, String authorityId) {
		Asserts.notEmpty(resourceId);
		Asserts.notEmpty(authorityId);
		
		ResourceAuthority resourceAuthority=new ResourceAuthority();
		resourceAuthority.setId(UUID.randomUUID().toString());
		resourceAuthority.setAuthorityId(authorityId);
		resourceAuthority.setResourceId(resourceId);
		
		resourceAuthorityMapper.insert(resourceAuthority);
	}
	
}
