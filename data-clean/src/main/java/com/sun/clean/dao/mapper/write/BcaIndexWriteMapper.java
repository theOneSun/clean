package com.sun.clean.dao.mapper.write;

import com.sun.clean.domain.write.BcaIndexWrite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @authur sunjian.
 */
@Mapper
public interface BcaIndexWriteMapper
{
    int insertList(@Param("writeList") List<BcaIndexWrite> bcaIndexWriteList);
}
