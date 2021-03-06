package com.sun.clean.utils;

import com.sun.clean.domain.read.AgeRead;
import com.sun.clean.domain.read.AvgRatioRead;
import com.sun.clean.domain.read.AvgShopNumRead;
import com.sun.clean.domain.read.BcaIndexRead;
import com.sun.clean.domain.read.BcaInformationRead;
import com.sun.clean.domain.read.BcaSituationRead;
import com.sun.clean.domain.read.CommentDataRead;
import com.sun.clean.domain.read.CommentRead;
import com.sun.clean.domain.read.CommentShareRead;
import com.sun.clean.domain.read.GenderRead;
import com.sun.clean.domain.read.RentalRead;
import com.sun.clean.domain.read.ShopsRead;
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
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 读写对象之间的转换
 *
 * @authur sunjian.
 */
@Service
public class ReadToWriteConvert
{
    public List<AgeWrite> getWriteFromRead(AgeRead read, List<AgeRead> ageReadList)
    {
        List<AgeWrite> ageWriteList = new ArrayList<>();
        for (AgeRead ageRead : ageReadList)
        {
            ageWriteList.add(new AgeWrite(ageRead.getId(), ageRead.getBusinessDistrictId(), ageRead.getCaPersonaAge_0_19(), ageRead
                    .getCaPersonaAge_20_29(), ageRead.getCaPersonaAge_30_39(), ageRead.getCaPersonaAge_40_49(), ageRead.getCaPersonaAge_50_59(), ageRead
                    .getCaPersonaAge_60_above(), ageRead.getBatch().toString()));
        }
        return ageWriteList;
    }

    public List<AvgRatioWrite> getWriteFromRead(AvgRatioRead read, List<AvgRatioRead> avgRatioReadList)
    {
        List<AvgRatioWrite> avgRatioWriteList = new ArrayList<>();
        for (AvgRatioRead avgRatioRead : avgRatioReadList)
        {
            avgRatioWriteList.add(new AvgRatioWrite(avgRatioRead.getId(), avgRatioRead.getBusinessDistrictId(), avgRatioRead
                    .getCaShopStyles(), avgRatioRead.getReviewsPre(), avgRatioRead.getGroup()));
        }
        return avgRatioWriteList;
    }

    public List<AvgShopNumWrite> getWriteFromRead(AvgShopNumRead read, List<AvgShopNumRead> avgShopNumReadList)
    {
        List<AvgShopNumWrite> avgShopNumWriteList = new ArrayList<>();
        for (AvgShopNumRead avgShopNumRead : avgShopNumReadList)
        {
            avgShopNumWriteList.add(new AvgShopNumWrite(avgShopNumRead.getId(), avgShopNumRead.getBusinessDistrictId(),
                    avgShopNumRead.getMealType(), TypeConvertUtils.convertLongToDouble(avgShopNumRead.getNumber()), avgShopNumRead.getGroup()));
        }
        return avgShopNumWriteList;
    }

    public List<BcaIndexWrite> getWriteFromRead(BcaIndexRead read, List<BcaIndexRead> bcaIndexReadList) throws ParseException
    {
        List<BcaIndexWrite> bcaIndexWriteList = new ArrayList<>();
        for (BcaIndexRead bcaIndexRead : bcaIndexReadList)
        {
            bcaIndexWriteList.add(new BcaIndexWrite(bcaIndexRead.getId(),
                    bcaIndexRead.getBusinessDistrictId(),
                    TypeConvertUtils.convertStringToTimestamp(bcaIndexRead.getDate()),
                    TypeConvertUtils.convertIntegerToDouble(bcaIndexRead.getShuntIndex())));
        }
        return bcaIndexWriteList;
    }

    public List<BcaInformationWrite> getWriteFromRead( BcaInformationRead read, List<BcaInformationRead> bcaInformationReadList)
    {
        List<BcaInformationWrite> bcaInformationWriteList = new ArrayList<>();
        for (BcaInformationRead bcaInformationRead : bcaInformationReadList)
        {
            bcaInformationWriteList.add(new BcaInformationWrite(bcaInformationRead.getId(), bcaInformationRead.getBusinessDistrictId(),
                    bcaInformationRead.getLessThanForty(), bcaInformationRead.getFortyEighty(), bcaInformationRead.getFortyGreathundred(),
                    bcaInformationRead.getGreathundredEightscore(), bcaInformationRead.getGreaterEightscore(), bcaInformationRead.getBatch()));
        }
        return bcaInformationWriteList;
    }

    public List<BcaSituationWrite> getWriteFromRead(BcaSituationRead read, List<BcaSituationRead> bcaSituationReadList)
    {
        List<BcaSituationWrite> bcaSituationWriteList = new ArrayList<>();
        for (BcaSituationRead bcaSituationRead : bcaSituationReadList)
        {
            bcaSituationWriteList.add(new BcaSituationWrite(bcaSituationRead.getId(), bcaSituationRead.getBusinessDistrictId(),
                    bcaSituationRead.getMealType(), bcaSituationRead.getNumber(), bcaSituationRead.getGroup()));
        }
        return bcaSituationWriteList;
    }

    public List<CommentWrite> getWriteFromRead(CommentRead read, List<CommentRead> commentReadList)
    {
        List<CommentWrite> commentWriteList = new ArrayList<>();
        for (CommentRead commentRead : commentReadList)
        {
            commentWriteList.add(new CommentWrite(commentRead.getId(), commentRead.getSrcShopId(),
                    commentRead.getDesShopId(), Integer.valueOf(commentRead.getCount()), commentRead.getBatch()));
        }
        return commentWriteList;
    }

    public List<CommentDataWrite> getWriteFromRead(CommentDataRead commentDataRead, List<CommentDataRead> readList)
    {
        List<CommentDataWrite> writeList = new ArrayList<>();
        for (CommentDataRead read : readList)
        {
            writeList.add(new CommentDataWrite(read.getId(), read.getBusinessDistrictId(), read.getVegetableType(),
                    read.getLessThanForty(), read.getFortyEighty(), read.getFortyGreathundred(),
                    read.getGreathundredEightscore(), read.getGreaterEightscore(), read.getBatch()));
        }
        return writeList;
    }

    public List<CommentShareWrite> getWriteFromRead(CommentShareRead commentShareRead, List<CommentShareRead> readList)
    {
        List<CommentShareWrite> writeList = new ArrayList<>();
        for (CommentShareRead read : readList)
        {
            writeList.add(new CommentShareWrite(read.getId(), read.getBusinessDistrictId(), read.getCaShopStyles(),
                    read.getReviewsPre(), read.getGroup()));
        }
        return writeList;
    }

    public List<GenderWrite> getWriteFromRead(GenderRead genderRead, List<GenderRead> readList)
    {
        List<GenderWrite> writeList = new ArrayList<>();
        for (GenderRead read : readList)
        {
            writeList.add(new GenderWrite(read.getId(), read.getBusinessDistrictId(), read.getCaPersonaGenderFemale().toString(),
                    read.getCaPersonaGenderMale().toString(), read.getBatch().toString()));
        }
        return writeList;
    }

    public List<RentalWrite> getWriteFromRead(RentalRead rentalRead, List<RentalRead> readList)
    {
        List<RentalWrite> writeList = new ArrayList<>();
        for (RentalRead read : readList)
        {
            writeList.add(new RentalWrite(read.getId(), read.getBusinessDistrictId(), read.getCaName(),
                    read.getCaRentalStreet(), read.getCaRentalOfficeBuilding(), read.getCaRentalResidence(),
                    read.getCaRentalAverage(), read.getBatch().toString()));
        }
        return writeList;
    }

    public List<ShopsWrite> getWriteFromRead(ShopsRead shopsRead, List<ShopsRead> readList)
    {
        List<ShopsWrite> writeList = new ArrayList<>();
        for (ShopsRead read : readList)
        {
            String caShopReviews = read.getCaShopReviews();
            String caShopReviewsSub = caShopReviews.substring(0, caShopReviews.indexOf("."));
            writeList.add(new ShopsWrite(read.getId(), read.getBusinessDistrictId(), read.getCaShopName(),
                    read.getCaShopStyles(), Double.valueOf(read.getCaShopAvgprice()), Integer.valueOf(caShopReviewsSub),
                    read.getCaShopReviewersGroup()));
        }
        return writeList;
    }
}
