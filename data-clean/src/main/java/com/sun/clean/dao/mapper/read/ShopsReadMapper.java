package com.sun.clean.dao.mapper.read;

import com.sun.clean.domain.read.ShopsRead;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @authur sunjian.
 */
@Mapper
public interface ShopsReadMapper
{
    /**
     * 获得重复的数据的重复参数
     *
     * @return
     */
    List<ShopsRead> getRedundantGroupList();

    /**
     * 根据参数查出所有重复数据
     *
     * @param shopsReadList
     * @return
     */
    List<ShopsRead> getAllDeleteList(@Param("shopsReadList") List<ShopsRead> shopsReadList);

    /**
     * 查询所有
     *
     * @return
     */
    List<ShopsRead> getAll();

    /**
     * 根据临时表去查重复数据
     *
     * @return
     */
    List<ShopsRead> getAllDeleteByTemp();

    /**
     * 创建临时表
     */
    void createTempTable();

    /**
     * 删除临时表
     */
    void dropTempTable();

    /**
     * 删除重复数据
     *
     * @param deleteList
     * @return
     */
    int deleteRedundant(@Param("deleteList") List<String> deleteList);
}
