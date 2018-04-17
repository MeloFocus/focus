package com.melo.focus.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.melo.focus.domain.basic.UserRoleR;

@Mapper
public interface UserRoleRExtendMapper {

	/**
	 * 批量新增用户角色关系
	 */
	public void batchInsert(@Param(value="userRoleList")List<UserRoleR> userRoleList);
	
	/**
	 * 根据集合中的user对象id，删除这些用户下所有的角色关系
	 */
	public void deleteByUserId(@Param(value="userIdList")List<String> userIdList);
	
	/**
	 * 根据roleId 删除 用户角色关系
	 */
	public void deleteByRoleId(@Param(value="roleId")String roleId);
	
	/**
	 * 根据‘用户id查所拥有的角色
	 */
	public List<String>getRoleByUser(@Param(value="userId")String userId);
}
