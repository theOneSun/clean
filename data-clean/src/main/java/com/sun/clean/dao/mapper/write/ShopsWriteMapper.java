package com.sun.clean.dao.mapper.write;

import com.sun.clean.domain.write.ShopsWrite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @authur sj.
 */
@Mapper
public interface ShopsWriteMapper {
    int insertList(@Param("writeList") List<ShopsWrite> writeList);
}
