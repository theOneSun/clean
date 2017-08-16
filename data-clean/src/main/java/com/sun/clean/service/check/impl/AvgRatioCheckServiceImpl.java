package com.sun.clean.service.check.impl;

import com.sun.clean.domain.write.AvgRatioWrite;
import com.sun.clean.service.check.AvgRatioCheckService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @authur sunjian.
 */
@Service
public class AvgRatioCheckServiceImpl extends BaseCheckImpl<AvgRatioWrite> implements AvgRatioCheckService
{
    @Override
    public List<AvgRatioWrite> checkRepeat(List<AvgRatioWrite> checkList)
    {
        //收集重复元素的集合
        List<AvgRatioWrite> repeatList = new ArrayList<>();

        //进行分组
        Map<String, Map<Integer, Map<String, List<AvgRatioWrite>>>> groupMap =
                checkList.stream().collect(Collectors.groupingBy(AvgRatioWrite::getBusinessDistrictId,
                        Collectors.groupingBy(AvgRatioWrite::getCaShopReviewersGroup,
                                Collectors.groupingBy(AvgRatioWrite::getCaShopStyles))));

        //遍历将重复的取出添加到重复集合中
        Set<Map.Entry<String, Map<Integer, Map<String, List<AvgRatioWrite>>>>> districtEntrySet = groupMap.entrySet();
        for (Map.Entry districtEntry : districtEntrySet)
        {
            //获取的是中间的map,key是导入批次
            Map<Integer,Map<String,List<AvgRatioWrite>>> indexMap = (Map<Integer,Map<String,List<AvgRatioWrite>>>) districtEntry.getValue();
            Set<Map.Entry<Integer, Map<String, List<AvgRatioWrite>>>> indexEntrySet = indexMap.entrySet();
            for (Map.Entry indexEntry : indexEntrySet)
            {
                //获取内部的map,key是菜系
                Map<String,List<AvgRatioWrite>> styleMap = (Map<String, List<AvgRatioWrite>>) indexEntry.getValue();
                //遍历判断list
                Set<Map.Entry<String, List<AvgRatioWrite>>> styleEntrySet = styleMap.entrySet();
                for (Map.Entry styleEntry : styleEntrySet)
                {
                    List<AvgRatioWrite> valueList = (List<AvgRatioWrite>) styleEntry.getValue();
                    if (valueList != null && valueList.size()>1){
                        repeatList.addAll(valueList);
                    }
                }
            }
        }
        return repeatList;
    }
}
