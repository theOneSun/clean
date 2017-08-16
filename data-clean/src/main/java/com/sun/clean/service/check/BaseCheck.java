package com.sun.clean.service.check;

import com.sun.clean.domain.BusinessDistrict;

import java.util.List;
import java.util.Set;

/**
 * Created by sj on 2017/7/24.
 */
public interface BaseCheck<T>
{
    /**
     * 非空校验
     *
     * @param checkList 校验的集合
     * @return 返回有空的数据的集合
     */
    List<T> checkEmpty(List<T> checkList) throws IllegalAccessException;

    /**
     * 数据重复校验
     *
     * @param checkList 校验的集合
     * @return 返回重复的数据
     */
    List<T> checkRepeat(List<T> checkList);

    /**
     * 缺失商圈校验
     *
     * @return
     */
    List<BusinessDistrict> checkLackBusinessDistrict(Set<String> checkIdSet);
}
