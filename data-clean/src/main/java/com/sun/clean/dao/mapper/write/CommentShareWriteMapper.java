package com.sun.clean.dao.mapper.write;

import com.sun.clean.domain.write.CommentShareWrite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @authur sj.
 */
@Mapper
public interface CommentShareWriteMapper {
    int insertList(@Param("writeList") List<CommentShareWrite> writeList);
}
