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
import java.util.Set;


/**
 * @authur sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckToOpenVO
{
    private CheckResult<AgeWrite> ageCheckResult;
    private CheckResult<AvgRatioWrite> avgRatioCheckResult;
    private CheckResult<AvgShopNumWrite> avgShopNumCheckResult;
    private CheckResult<BcaIndexWrite> bcaIndexCheckResult;
    private CheckResult<BcaInformationWrite> bcaInformationCheckResult;
    private CheckResult<BcaSituationWrite> bcaSituationCheckResult;
    private CheckResult<CommentDataWrite> commentDataCheckResult;
    private CheckResult<CommentWrite> commentCheckResult;
    private CheckResult<CommentShareWrite> commentShareCheckResult;
    private CheckResult<GenderWrite> genderCheckResult;
    private CheckResult<RentalWrite> rentalCheckResult;
    private CheckResult<ShopsWrite> shopsCheckResult;
    private List<Set<String>> businessDistrictIdSetList;
}
