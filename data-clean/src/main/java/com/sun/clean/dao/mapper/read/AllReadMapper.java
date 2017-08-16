package com.sun.clean.dao.mapper.read;

import com.sun.clean.domain.read.AgeRead;
import com.sun.clean.domain.read.AvgRatioRead;
import com.sun.clean.domain.read.AvgShopNumRead;
import com.sun.clean.domain.read.BcaIndexRead;
import com.sun.clean.domain.read.BcaInformationRead;
import com.sun.clean.domain.read.BcaSituationRead;
import com.sun.clean.domain.read.BusinessRead;
import com.sun.clean.domain.read.CommentDataRead;
import com.sun.clean.domain.read.CommentRead;
import com.sun.clean.domain.read.CommentShareRead;
import com.sun.clean.domain.read.GenderRead;
import com.sun.clean.domain.read.RentalRead;
import com.sun.clean.domain.read.ShopsRead;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 读取每张原始表表的全部数据
 * @authur sunjian.
 */
@Mapper
public interface AllReadMapper
{

    List<AgeRead> getAllAgeRead();

    List<AvgRatioRead> getAllAvgRatioRead();

    List<AvgShopNumRead> getAllAvgShopNumRead();

    List<BcaIndexRead> getAllBcaIndexRead();

    List<BcaInformationRead> getAllBcaInformationRead();

    List<BcaSituationRead> getAllBcaSituationRead();

    List<CommentRead> getAllCommentRead();

    List<CommentDataRead> getAllCommentDataRead();

    List<CommentShareRead> getAllCommentShareRead();

    List<GenderRead> getAllGenderRead();

    List<RentalRead> getAllRentalRead();

    List<ShopsRead> getAllShopsRead();

    List<BusinessRead> getAllBusinessRead();
}
