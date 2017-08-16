package com.sun.clean.dao.mapper.write;

import com.sun.clean.domain.write.AgeWrite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @authur sunjian.
 */
@Mapper
public interface AgeWriteMapper
{
    int insertList(@Param("ageWriteList") List<AgeWrite> ageWriteList);
}
