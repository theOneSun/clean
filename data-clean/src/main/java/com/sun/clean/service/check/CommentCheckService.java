package com.sun.clean.service.check;

import com.sun.clean.domain.write.CommentWrite;
import com.sun.clean.domain.write.ShopsWrite;

import java.util.List;
import java.util.Map;

/**
 * Created by sj on 2017/7/28.
 */
public interface CommentCheckService extends BaseCheck<CommentWrite>{

    /**
     * 校验外键字段
     *
     * @param commentWriteList 要插入的所有数据的集合
     * @param shopsWriteList   查询出的shops的集合
     * @return shopsId在src中查不到的--List<ShopsWrite>;desId在shopId查不到的--List<CommentWrite>
     */
    Map<String, List> checkForeignKey(List<CommentWrite> commentWriteList, List<ShopsWrite> shopsWriteList);
}
