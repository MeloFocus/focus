package com.melo.focus.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.melo.focus.domain.basic.Authority;
import com.melo.focus.domain.extend.AuthorityController;

@Mapper
public interface AuthorityExtendMapper {

	/** 
	 * 根据 权限id 查权限对象集
	 */
	public List<Authority>getAuthorityByIds(@Param(value="ids")List<String>ids);
	
	/**
	 * 按controller分组查 操作码列表
	 */
	public List<AuthorityController>getAuthority();
	
	/**
	 * 根据请求方式，关键字模糊查询
	 */
	public List<Authority>getLikeAuthority(@Param(value="method")String method,@Param(value="word")String word);
}
