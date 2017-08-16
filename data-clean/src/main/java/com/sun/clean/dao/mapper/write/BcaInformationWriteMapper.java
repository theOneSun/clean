package com.sun.clean.dao.mapper.write;

import com.sun.clean.domain.write.BcaInformationWrite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @authur sunjian.
 */
@Mapper
public interface BcaInformationWriteMapper
{
    int insertList(@Param("writeList") List<BcaInformationWrite> bcaInformationWriteList);
}
