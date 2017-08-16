package com.sun.clean.service.check.impl;

import com.sun.clean.domain.write.AvgShopNumWrite;
import com.sun.clean.service.check.AvgShopNumCheckService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @authur sunjian.
 */
public class AvgShopNumCheckServiceImpl extends BaseCheckImpl<AvgShopNumWrite> implements AvgShopNumCheckService
{
    @Override
    public List<AvgShopNumWrite> checkRepeat(List<AvgShopNumWrite> checkList)
    {
        List<AvgShopNumWrite> repeatList = new ArrayList<>();
        //进行分组
        Map<String, Map<Integer, Map<String, List<AvgShopNumWrite>>>> groupMap =
                checkList.stream().collect(Collectors.groupingBy(AvgShopNumWrite::getBusinessDistrictId,
                        Collectors.groupingBy(AvgShopNumWrite::getCaShopReviewersGroup,
                                Collectors.groupingBy(AvgShopNumWrite::getCaShopStyles))));
        //找重复的
        Set<Map.Entry<String, Map<Integer, Map<String, List<AvgShopNumWrite>>>>> districtEntrySet = groupMap.entrySet();
        for (Map.Entry districtEntry : districtEntrySet)
        {
            Map<Integer, Map<String, List<AvgShopNumWrite>>> districtMapValue =
                    (Map<Integer, Map<String, List<AvgShopNumWrite>>>) districtEntry.getValue();
            Set<Map.Entry<Integer, Map<String, List<AvgShopNumWrite>>>> indexEntrySet = districtMapValue.entrySet();
            for (Map.Entry indexEntry : indexEntrySet)
            {
                Map<String, List<AvgShopNumWrite>> indexMapValue = (Map<String, List<AvgShopNumWrite>>) indexEntry.getValue();
                Set<Map.Entry<String, List<AvgShopNumWrite>>> styleEntrySet = indexMapValue.entrySet();
                for (Map.Entry styleEntry : styleEntrySet)
                {
                    List<AvgShopNumWrite> valueList = (List<AvgShopNumWrite>) styleEntry.getValue();
                    if (valueList != null && valueList.size() > 1)
                    {
                        repeatList.addAll(valueList);
                    }
                }
            }
        }
        return repeatList;
    }
}
