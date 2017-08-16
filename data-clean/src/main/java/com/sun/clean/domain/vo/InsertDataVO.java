package com.sun.clean.domain.vo;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @authur sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertDataVO
{
    private List<AgeWrite> ageWriteList;
    private List<AvgRatioWrite> avgRatioWriteList;
    private List<AvgShopNumWrite> avgShopNumWriteList;
    private List<BcaIndexWrite> bcaIndexWriteList;
    private List<BcaInformationWrite> bcaInformationWriteList;
    private List<BcaSituationWrite> bcaSituationWriteList;
    private List<CommentWrite> commentWriteList;
    private List<CommentDataWrite> commentDataWriteList;
    private List<CommentShareWrite> commentShareWriteList;
    private List<GenderWrite> genderWriteList;
    private List<RentalWrite> rentalWriteList;
    private List<ShopsWrite> shopsWriteList;
}
