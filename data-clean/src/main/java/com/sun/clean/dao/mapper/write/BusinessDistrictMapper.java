package com.sun.clean.dao.mapper.write;

import com.sun.clean.domain.BusinessDistrict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @authur sunjian.
 */
@Mapper
public interface BusinessDistrictMapper
{
    /**
     * 查询所有开放的商圈
     *
     * @return
     */
    List<BusinessDistrict> getAllOpen();

    /**
     * 查询所有商圈
     *
     * @return
     */
    List<BusinessDistrict> getAll();

    /**
     * 查询所有id
     * @return
     */
    Set<String> getAllId();

    /**
     * 开放商圈
     *
     * @param id 商圈id
     */
    void setOpen(@Param("id") String id);

    /**
     * 根据城市查询开放的商圈
     *
     * @param cityList
     * @return
     */
    List<BusinessDistrict> getOpenByCity(@Param("cityList") List<String> cityList);

    /**
     * 批量开放商圈
     *
     * @param openIdList 要开放商圈的集合
     */
    int setOpenBatch(@Param("openIdList") Set<String> openIdList);

    /**
     * 根据id集合查询商圈集合
     *
     * @param businessDistrictIdSet 商圈id集合
     * @return
     */
    List<BusinessDistrict> getList(@Param("idSet") Set<String> businessDistrictIdSet);

    /**
     * 新增商圈
     *
     * @param businessDistrictList
     * @return
     */
    int insertList(@Param("list") List<BusinessDistrict> businessDistrictList);
}
