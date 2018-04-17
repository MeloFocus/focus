package com.melo.focus.mapper.basic;

import com.melo.focus.domain.basic.ResourceAuthority;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ResourceAuthorityMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResourceAuthority record);

    ResourceAuthority selectByPrimaryKey(String id);

    List<ResourceAuthority> selectAll();

    int updateByPrimaryKey(ResourceAuthority record);
}