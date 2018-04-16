package com.melo.focus.mapper.basic;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.melo.focus.domain.basic.UserRoleR;
@Mapper
public interface UserRoleRMapper {
    int insert(UserRoleR record);

    List<UserRoleR> selectAll();
}