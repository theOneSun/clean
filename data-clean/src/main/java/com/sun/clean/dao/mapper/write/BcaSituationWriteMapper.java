package com.sun.clean.dao.mapper.write;

import com.sun.clean.domain.write.BcaSituationWrite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @authur sunjian.
 */
@Mapper
public interface BcaSituationWriteMapper
{
    int insertList(@Param("writeList") List<BcaSituationWrite> bcaSituationWriteList);
}
