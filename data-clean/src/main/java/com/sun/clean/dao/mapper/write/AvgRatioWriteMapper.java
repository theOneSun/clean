package com.sun.clean.dao.mapper.write;

import com.sun.clean.domain.write.AvgRatioWrite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @authur sunjian.
 */
@Mapper
public interface AvgRatioWriteMapper
{
    int insertList(@Param("writeList") List<AvgRatioWrite> avgRatioWriteList);
}
