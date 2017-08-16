package com.sun.clean.dao.mapper.write;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @authur sunjian.
 */
public interface WriteMapper<T>
{
    int insertList(@Param("writeList") List<T> writeList);
}
