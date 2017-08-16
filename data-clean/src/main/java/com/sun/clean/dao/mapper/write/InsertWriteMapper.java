package com.sun.clean.dao.mapper.write;

import com.sun.clean.domain.write.AgeWrite;
import com.sun.clean.domain.write.AvgRatioWrite;
import com.sun.clean.domain.write.AvgShopNumWrite;
import com.sun.clean.domain.write.BcaIndexWrite;
import com.sun.clean.domain.write.BcaInformationWrite;
import com.sun.clean.domain.write.BcaSituationWrite;
import com.sun.clean.domain.write.CommentDataWrite;
import com.sun.clean.domain.write.CommentShareWrite;
import com.sun.clean.domain.write.CommentWrite;
import com.sun.clean.domain.write.GenderWrite;
import com.sun.clean.domain.write.RentalWrite;
import com.sun.clean.domain.write.ShopsWrite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 插入记录的mapper
 * @authur sunjian.
 */
@Mapper
public interface InsertWriteMapper
{
    void saveAgeWrite(@Param("age") AgeWrite ageWrite);

    void saveAvgRatioWrite(@Param("ratio") AvgRatioWrite avgRatioWrite);

    void saveAvgShopNumWrite(@Param("shopNum") AvgShopNumWrite avgShopNumWrite);

    void saveBcaIndexWrite(@Param("index") BcaIndexWrite bcaIndexWrite);

    void saveBcaInformationWrite(@Param("information") BcaInformationWrite bcaInformationWrite);

    void saveBcaSituationWrite(@Param("situation") BcaSituationWrite bcaSituationWrite);

    void saveCommentDataWrite(@Param("commentData") CommentDataWrite commentDataWrite);

    void saveCommentShareWrite(@Param("commentShare") CommentShareWrite commentShareWrite);

    void saveCommentWrite(@Param("comment") CommentWrite commentWrite);

    void saveGenderWrite(@Param("gender") GenderWrite genderWrite);

    void saveRentalWrite(@Param("rental") RentalWrite rentalWrite);

    void saveShopsWrite(@Param("shops") ShopsWrite shopsWrite);






}
