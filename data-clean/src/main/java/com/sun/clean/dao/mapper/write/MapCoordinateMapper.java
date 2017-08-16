package com.sun.clean.dao.mapper.write;

import com.sun.clean.domain.MapCoordinate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @authur sunjian.
 */
@Mapper
public interface MapCoordinateMapper
{
    /**
     * 查询所有
     * @return
     */
    List<MapCoordinate> getAll();

    /**
     * 查询所有商圈的id
     * @return 去重后的集合
     */
    Set<String> getAllBusinessDistrictId();
}
