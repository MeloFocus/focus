package com.melo.focus.mapper.basic;

import com.melo.focus.domain.basic.RoleBusiness;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface RoleBusinessMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleBusiness record);

    RoleBusiness selectByPrimaryKey(String id);

    List<RoleBusiness> selectAll();

    int updateByPrimaryKey(RoleBusiness record);
}