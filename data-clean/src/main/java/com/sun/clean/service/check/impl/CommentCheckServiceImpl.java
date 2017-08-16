package com.sun.clean.service.check.impl;

import com.sun.clean.constant.ErrorMessage;
import com.sun.clean.domain.write.CommentWrite;
import com.sun.clean.domain.write.ShopsWrite;
import com.sun.clean.service.check.CommentCheckService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sj on 2017/7/28.
 */
@Service
public class CommentCheckServiceImpl extends BaseCheckImpl<CommentWrite> implements CommentCheckService{
    @Override
    public List<CommentWrite> checkRepeat(List<CommentWrite> checkList) {
        List<CommentWrite> repeatList = new ArrayList<>();
        //分组
        Map<String, Map<Integer, Map<String, List<CommentWrite>>>> groupMap = checkList.stream()
                .collect(Collectors.groupingBy(CommentWrite::getSrcShopId, Collectors
                        .groupingBy(CommentWrite::getBatch, Collectors
                                .groupingBy(CommentWrite::getDesShopId))));

        //有重复
        Set<Map.Entry<String, Map<Integer, Map<String, List<CommentWrite>>>>> districtEntrySet = groupMap.entrySet();
        for (Map.Entry districtEntry : districtEntrySet)
        {
            Map<Integer,Map<String,List<CommentWrite>>> districtMap = (Map<Integer, Map<String, List<CommentWrite>>>) districtEntry.getValue();
            Set<Map.Entry<Integer, Map<String, List<CommentWrite>>>> indexEntrySet = districtMap.entrySet();
            for (Map.Entry indexEntry : indexEntrySet)
            {
                Map<String, List<CommentWrite>> desMap = (Map<String, List<CommentWrite>>) indexEntry.getValue();
                Set<Map.Entry<String, List<CommentWrite>>> desEntrySet = desMap.entrySet();
                for (Map.Entry desEntry : desEntrySet)
                {
                    List<CommentWrite> valueList = (List<CommentWrite>) desEntry.getValue();
                    if (valueList.size() > 1)
                    {
                        repeatList.addAll(valueList);
                    }
                }
            }
        }
        return repeatList;
    }

    @Override
    public Map<String, List> checkForeignKey(List<CommentWrite> commentWriteList, List<ShopsWrite> shopsWriteList) {
        /*
        1.根据shopsId去查询srcId,若是没有返回List<ShopsWrite>
        2.根据desId去查询shopsId,若是没有返回List<CommentWrite>
         */
        // TODO: 2017/7/28 重写此方法,参数为checkResult,结果返回checkResult
        Map<String, List> resultMap = new HashMap<>();
        Set<String> shopsIdSet = new HashSet<>();
        Set<String> srcIdSet = new HashSet<>();

        List<ShopsWrite> shopsIdNotFoundInSrcList = new ArrayList<>();
        List<CommentWrite> desIdNotFoundInShopsList = new ArrayList<>();

        for (CommentWrite commentWrite : commentWriteList)
        {
            //srcIdList.add(commentWrite.getSrcShopId());
            srcIdSet.add(commentWrite.getSrcShopId());
        }
        //校验shopsId在srcId中是否存在
        String shopsId;
        for (ShopsWrite shopsWrite : shopsWriteList)
        {
            shopsId = shopsWrite.getId();

            shopsIdSet.add(shopsId);

            if (!srcIdSet.contains(shopsId)){
                shopsIdNotFoundInSrcList.add(shopsWrite);
            }
        }
        if (shopsIdNotFoundInSrcList.size() > 0)
        {
            //shopsId在srcId中没有匹配
            resultMap.put(ErrorMessage.COMMENT_CHECK_SHOPS_ID_NO_FOUND.getMessage(), shopsIdNotFoundInSrcList);
        }
        //校验desId在shopsId中是否存在
        for (CommentWrite commentWrite : commentWriteList)
        {
            if (shopsIdSet.contains(commentWrite.getDesShopId())){
                desIdNotFoundInShopsList.add(commentWrite);
            }
        }
        if (desIdNotFoundInShopsList.size() > 0)
        {
            //desId在shopsId中没有匹配
            resultMap.put(ErrorMessage.COMMENT_CHECK_DES_ID_NO_FOUND.getMessage(), desIdNotFoundInShopsList);
        }
        return resultMap;
    }
}
