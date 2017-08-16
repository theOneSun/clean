package com.sun.clean.dao.mapper.write;

import com.sun.clean.domain.Environment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @authur sunjian.
 */
@Mapper
public interface EnvironmentMapper
{
    /**
     * 查询所有
     * @return
     */
    List<Environment> getAll();

    /**
     * 查询所有商圈id
     * @return 去重后的集合
     */
    Set<String> getAllBusinessDistrictId();
}
