package com.sun.clean.processor;

import com.sun.clean.dao.mapper.write.AgeWriteMapper;
import com.sun.clean.domain.BusinessDistrict;
import com.sun.clean.domain.read.AgeRead;
import com.sun.clean.domain.vo.CheckResult;
import com.sun.clean.domain.write.AgeWrite;
import com.sun.clean.service.check.AgeCheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @authur sunjian.
 */
@Service
public class AgeProcessor extends ProcessorImpl<AgeWrite,AgeRead>
{

    @Resource
    private AgeCheckService ageCheckService;

    @Resource
    private AgeWriteMapper ageWriteMapper;

    @Override
    public List<AgeRead> getAllRead()
    {
        return allReadMapper.getAllAgeRead();
    }

    @Override
    public List<AgeWrite> formatData(List<AgeRead> readList)
    {
        return readToWriteConvert.getWriteFromRead(new AgeRead(), readList);
    }

    @Override
    public CheckResult<AgeWrite> checkData(List<AgeWrite> writeList) throws IllegalAccessException
    {
        /*
        1.校验商圈是否缺失--筛选出缺失的商圈lackList<businessDistrict>,未开放商圈的数据redundantList<AgeWrite>
        2.非空校验
        3.数据重复校验
        ..待补充
         */
        //writeList中商圈ID的集合
        Set<String> checkIdSet = new HashSet<>();
        //获取待校验的ID集合
        String writeBusinessDistrictId;
        //未开放商圈的数据
        List<AgeWrite> redundantList = new ArrayList<>();

//        CheckResult<AgeWrite> ageWriteCheckResult = new CheckResult<>();
        //有空的数据
        List<AgeWrite> emptyList = ageCheckService.checkEmpty(writeList);
//        ageWriteCheckResult.setEmptyList(emptyList);
        //数据有重复的
        List<AgeWrite> repeatList = ageCheckService.checkRepeat(writeList);
//        ageWriteCheckResult.setRepeatList(repeatList);
        //------校验商圈是否缺失--------------------------
        for (AgeWrite ageWrite : writeList)
        {
            writeBusinessDistrictId = ageWrite.getBusinessDistrictId();
            if (!checkIdSet.contains(writeBusinessDistrictId)){
                checkIdSet.add(writeBusinessDistrictId);
            }
        }
        /*
        商圈校验:
        1.获取开放的商圈
        2.获取writeList的商圈id集合并去重-->checkBusinessIdList
        3.遍历开放商圈,如果checkList包含其id,说明有此商圈数据,checkList.remove(thisId);
            若不包含,说明缺失此商圈的数据,lackList.add()
         */
        List<BusinessDistrict> lackList = ageCheckService.checkLackBusinessDistrict(checkIdSet);
//        ageWriteCheckResult.setLackList(lackList);

        // 找出未开放商圈的数据
        if (checkIdSet.size() > 0)
        {
            //说明数据中含有未开放的商圈
            for (AgeWrite ageWrite : writeList)
            {
                if (checkIdSet.contains(ageWrite.getBusinessDistrictId()))
                {
                    //说明这条记录是未开放商圈的
                    redundantList.add(ageWrite);
                }
            }
        }
//        ageWriteCheckResult.setRedundantList(redundantList);
        if (emptyList != null)
        {
            writeList.removeAll(emptyList);
        }
        if (repeatList != null)
        {
            writeList.removeAll(repeatList);
        }
        writeList.removeAll(redundantList);
//        ageWriteCheckResult.setImportDataList(importDataList);

        return new CheckResult.Builder<AgeWrite>().importDataList(writeList)
                                                  .redundantList(redundantList)
                                                  .emptyList(emptyList)
                                                  .repeatList(repeatList)
                                                  .lackList(lackList)
                                                  .build();
    }

    @Override
    public int importData(List<AgeWrite> importDataList)
    {
        int count = 0;

        if (importDataList == null || importDataList.size() == 0){
            //没有导入的数据
            return 0;
        }

        int size = importDataList.size();
        if (size > 3000)
        {
            int result = size / 3000;//商
            int remainder = size % 3000;//余数
            if (remainder > 0)
            {
                //没有整除,循环result+1次
                result += 1;
            }
            for (int i = 0; i < result; i++)
            {
                List<AgeWrite> currentImportList = importDataList.stream()
                                                                 .skip(i * 3000)
                                                                 .limit(3000)
                                                                 .collect(Collectors.toList());
                count += ageWriteMapper.insertList(currentImportList);
            }
        } else
        {
            count = ageWriteMapper.insertList(importDataList);
        }
        return count;
    }
}
