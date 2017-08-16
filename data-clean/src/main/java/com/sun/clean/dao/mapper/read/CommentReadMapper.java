package com.sun.clean.dao.mapper.read;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @authur sunjian.
 */
@Mapper
public interface CommentReadMapper
{
    /**
     * 查询srcShopId集合
     *
     * @return
     */
    List<String> getSrcId();

    /**
     * 查询desShopId集合
     *
     * @return
     */
    List<String> getDesId();
}
