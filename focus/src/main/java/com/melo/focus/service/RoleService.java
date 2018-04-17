package com.melo.focus.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melo.focus.domain.basic.Role;
import com.melo.focus.domain.basic.RoleResourcer;
import com.melo.focus.domain.vm.RoleResourceVM;
import com.melo.focus.domain.vm.RoleSaveVM;
import com.melo.focus.domain.vm.RoleUpdateVM;
import com.melo.focus.mapper.basic.RoleMapper;
import com.melo.focus.mapper.extend.RoleExtendMapper;
import com.melo.focus.util.Asserts;
import com.melo.focus.util.DataValidateFiledException;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	RoleResourceService roleResourceService;
	
	@Autowired
	RoleExtendMapper roleExtendMapper;
	
	public void saveRole(RoleSaveVM roleSaveVM) {
		Asserts.validate(roleSaveVM,"roleSaveVM");
		
		Role role=new Role();
		role.setId(UUID.randomUUID().toString());
		role.setCode(roleSaveVM.getCode());
		role.setDes(roleSaveVM.getDes());
		role.setName(roleSaveVM.getName());
		role.setSort(roleSaveVM.getSort());
		role.setType(roleSaveVM.getType());
		
		roleMapper.insert(role);
	}

	public void deleteRole(String roleId) {
		if(StringUtils.isBlank(roleId)){
			throw new DataValidateFiledException("角色id不能为空");
		}
		
		//校验roleId  是否存在
		
		//删角色和用户的关系
		userRoleService.deleteByRoleId(roleId);
		//删角色和资源的关系
		roleResourceService.deleteRoleResource(roleId,null);
		//删角色
		roleMapper.deleteByPrimaryKey(roleId);
	}

	/**
	 * 修改角色
	 * @param roleUpdateVM
	 */
	public void updateRole(RoleUpdateVM roleUpdateVM) {
		Asserts.validate(roleUpdateVM, "roleUpdateVM");
		//校验id是否存在
		
		Role role=new Role();
		role.setId(roleUpdateVM.getId());
		role.setCode(roleUpdateVM.getCode());
		role.setDes(roleUpdateVM.getDes());
		role.setName(roleUpdateVM.getName());
		role.setSort(roleUpdateVM.getSort());
		role.setType(roleUpdateVM.getType());
		
		roleMapper.updateByPrimaryKey(role);
	}

	public List<Role> getRoleList() {
		List<Role> roleList = roleMapper.selectAll();
		Collections.sort(roleList);
		return roleList;
	}

	public void authorize(RoleResourceVM roleResourceVM) {
		Asserts.validate(roleResourceVM, "roleResource");
		if(StringUtils.isBlank(roleResourceVM.getRoleIds())||StringUtils.isBlank(roleResourceVM.getResourceIds())){
			throw new DataValidateFiledException("角色或资源不能为空");
		}
		String roleIds=roleResourceVM.getRoleIds();
		String resourceIds=roleResourceVM.getResourceIds();
		
		List<String> resourceList = Arrays.asList(resourceIds.split(","));
		List<String>roleList =Arrays.asList(roleIds.split(","));
		
		List<RoleResourcer>roleResourceList=new ArrayList<RoleResourcer>();
		
		for(String roleId:roleList){
			
			for(String resourceId:resourceList){
				RoleResourcer roleResource=new RoleResourcer();
				roleResource.setId(UUID.randomUUID().toString());
				roleResource.setRoleId(roleId);
				roleResource.setResourceId(resourceId);
				roleResourceList.add(roleResource);
			}
			//删角色和资源的关系
			roleResourceService.deleteRoleResource(roleId,null);
		}
		
		roleResourceService.batchInsert(roleResourceList);
	}

	public Boolean isExist(String code) {
		return roleExtendMapper.isExist(code);
	}

}
