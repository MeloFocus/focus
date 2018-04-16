package com.melo.focus.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.melo.focus.domain.basic.RoleResourcer;

@Mapper
public interface RoleResourceExtendMapper {

	/**
	 * 根据roleid 或resourceid 删除角色资源关系
	 */
	public void deleteRoleResource(
			@Param(value="roleId")String roleId,
			@Param(value="resourceId")String resourceId);
	
	/**
	 * 批量保存 角色资源关系
	 */
	public void batchInsert(@Param(value="list")List<RoleResourcer> list);
	
	/**
	 * 通过roleid得到拥有的resourceid
	 */
	public List<String> getResourceByRole(@Param(value="roleIds")List<String>roleIds);
}
