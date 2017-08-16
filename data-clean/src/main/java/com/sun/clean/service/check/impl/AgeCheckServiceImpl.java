package com.sun.clean.service.check.impl;

import com.sun.clean.domain.write.AgeWrite;
import com.sun.clean.service.check.AgeCheckService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sj on 2017/7/24.
 */
@Service
public class AgeCheckServiceImpl extends BaseCheckImpl<AgeWrite> implements AgeCheckService {


    @Override
    public List<AgeWrite> checkRepeat(List<AgeWrite> checkList) {
        List<AgeWrite> repeatList = new ArrayList<>();
        //进行group by分组
        Map<String, Map<String, List<AgeWrite>>> groupMap =
                checkList.stream().collect(Collectors.groupingBy(AgeWrite::getBusinessDistrictId,
                        Collectors.groupingBy(AgeWrite::getCaPersonaGenderBatchIndex)));

        //调用方法找出重复的
        /*
            1.获得map中的list
            2.list的size>1的就是重复的
         */
        Set<Map.Entry<String, Map<String, List<AgeWrite>>>> entrySet = groupMap.entrySet();
        for (Map.Entry entry: entrySet)
        {
            Map<String, List<AgeWrite>> valueMap = (Map<String, List<AgeWrite>>) entry.getValue();
            Set<Map.Entry<String, List<AgeWrite>>> valueEntrySet = valueMap.entrySet();
            for (Map.Entry valueEntry : valueEntrySet)
            {
                List<AgeWrite> valueList = (List<AgeWrite>) valueEntry.getValue();
                if (valueList != null && valueList.size() > 1){
                    //该分组的集合长度大于1说明有重复的,将该集合所有元素添加到重复的元素集合中
                    repeatList.addAll(valueList);
                }
            }
        }
        return repeatList;
    }
}
