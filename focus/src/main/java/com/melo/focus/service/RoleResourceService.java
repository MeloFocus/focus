package com.melo.focus.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melo.focus.domain.basic.RoleResourcer;
import com.melo.focus.mapper.extend.RoleResourceExtendMapper;

@Service
@Transactional
public class RoleResourceService {
	
	@Autowired
	RoleResourceExtendMapper roleResourceExtendMapper;

	/**
	 * 根据roleid 或resourceid 删除角色和资源关系
	 */
	public void deleteRoleResource(String roleId,String resourceId){
		roleResourceExtendMapper.deleteRoleResource(roleId,null);
	}
	
	/**
	 * 批量新增角色资源关系
	 */
	public void batchInsert (List<RoleResourcer>list){
		roleResourceExtendMapper.batchInsert(list);
	}
	
	/**
	 * 根据role ID集合，查这些角色拥有的资源id
	 */
	public List<String>getResource(List<String>roleIds){
		if(roleIds!=null&&CollectionUtils.isNotEmpty(roleIds)){
			List<String> resourceIds = roleResourceExtendMapper.getResourceByRole(roleIds);
			return resourceIds;
		}
		return null;
	}
}
