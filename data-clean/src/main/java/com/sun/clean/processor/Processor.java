package com.sun.clean.processor;

import com.sun.clean.domain.vo.CheckResult;

import java.util.List;

/**
 * T是write的类,U是read的类
 *
 * @authur sunjian.
 */
public interface Processor<T, U>
{
    /**
     * 主执行方法
     *
     * @return checkResult
     * @throws IllegalAccessException
     */
    CheckResult<T> process() throws IllegalAccessException;

    /**
     * 读取
     *
     * @return
     */
    List<U> getAllRead();

    /**
     * 转换数据
     *
     * @param readList
     * @return
     */
    List<T> formatData(List<U> readList);

    /**
     * 校验封装数据
     *
     * @param writeList
     * @return
     * @throws IllegalAccessException
     */
    CheckResult<T> checkData(List<T> writeList) throws IllegalAccessException;

    /**
     * 导入数据
     *
     * @param importDataList
     * @return
     */
    int importData(List<T> importDataList);
}
