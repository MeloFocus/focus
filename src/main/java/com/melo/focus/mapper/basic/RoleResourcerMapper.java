package com.melo.focus.mapper.basic;

import com.melo.focus.domain.basic.RoleResourcer;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface RoleResourcerMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleResourcer record);

    RoleResourcer selectByPrimaryKey(String id);

    List<RoleResourcer> selectAll();

    int updateByPrimaryKey(RoleResourcer record);
}