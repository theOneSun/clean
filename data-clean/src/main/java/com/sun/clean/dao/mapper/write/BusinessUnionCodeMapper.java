package com.sun.clean.dao.mapper.write;

import com.sun.clean.domain.BusinessUnionCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @authur sunjian.
 */
@Mapper
public interface BusinessUnionCodeMapper
{
    List<BusinessUnionCode> getAll();
}
