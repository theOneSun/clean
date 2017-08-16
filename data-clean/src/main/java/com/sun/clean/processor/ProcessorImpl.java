package com.sun.clean.processor;

import com.sun.clean.dao.mapper.read.AllReadMapper;
import com.sun.clean.domain.vo.CheckResult;
import com.sun.clean.utils.ReadToWriteConvert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sj on 2017/7/24.
 */
@Service
public abstract class ProcessorImpl<T,U> implements Processor<T,U>{

    @Resource
    AllReadMapper allReadMapper;

    @Resource
    ReadToWriteConvert readToWriteConvert;

    /*
    1.1process(主执行方法)
	1.2读取
	1.3转换
	1.4校验(包括商圈的校验,非空,重复校验,特殊校验)
	1.5处理(校验处理可合并,目的是封装checkresult)
     */

    @Override
    public CheckResult<T> process() throws IllegalAccessException
    {
        List<U> readList = getAllRead();
        List<T> writeList = formatData(readList);
        return checkData(writeList);
    }

}
