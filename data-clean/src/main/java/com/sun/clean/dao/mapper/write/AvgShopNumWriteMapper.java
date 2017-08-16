package com.sun.clean.dao.mapper.write;


import com.sun.clean.domain.write.AvgShopNumWrite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @authur sunjian.
 */
@Mapper
public interface AvgShopNumWriteMapper
{
    int insertList(@Param("writeList") List<AvgShopNumWrite> avgShopNumWriteList);
}
