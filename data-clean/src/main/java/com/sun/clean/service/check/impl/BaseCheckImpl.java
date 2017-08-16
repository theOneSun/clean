package com.sun.clean.service.check.impl;

import com.sun.clean.dao.mapper.write.BusinessDistrictMapper;
import com.sun.clean.domain.BusinessDistrict;
import com.sun.clean.service.check.BaseCheck;
import com.sun.clean.utils.CheckUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by sj on 2017/7/24.
 */
@Service
public abstract class BaseCheckImpl<T> implements BaseCheck<T>
{
    @Resource
    BusinessDistrictMapper businessDistrictMapper;

    @Override
    public List<T> checkEmpty(List<T> checkList) throws IllegalAccessException
    {
        return CheckUtils.checkListEmpty(checkList);
    }

    @Override
    public List<BusinessDistrict> checkLackBusinessDistrict(Set<String> checkIdSet)
    {
        List<BusinessDistrict> lackList = new ArrayList<>();
        String businessDistrictId;
        List<BusinessDistrict> openBusinessDistrictList = businessDistrictMapper.getAllOpen();

        for (BusinessDistrict businessDistrict : openBusinessDistrictList)
        {
            businessDistrictId = businessDistrict.getId();
            if (checkIdSet.contains(businessDistrictId))
            {
                checkIdSet.remove(businessDistrictId);
            } else
            {
                //要导入的数据中不包含此商圈
                lackList.add(businessDistrict);
            }
        }
        return lackList;
    }
}
