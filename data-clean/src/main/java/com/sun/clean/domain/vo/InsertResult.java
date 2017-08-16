package com.sun.clean.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @authur sunjian.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertResult
{
    private Integer ageCount;
    private Integer avgRatioCount;
    private Integer avgShopCount;
    private Integer bcaIndexCount;
    private Integer bcaInformationCount;
    private Integer bcaSituationCount;
    private Integer commentCount;
    private Integer commentDataCount;
    private Integer commentShareCount;
    private Integer genderCount;
    private Integer rentalCount;
    private Integer shopsCount;
}
