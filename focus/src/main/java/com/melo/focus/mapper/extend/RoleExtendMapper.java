package com.melo.focus.mapper.extend;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleExtendMapper {

	/**
	 * 校验角色code唯一性
	 */
	Boolean isExist(@Param(value="code")String code);
}
