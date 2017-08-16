package com.sun.clean.domain.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @authur sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDataRead
{
    /*
    `id` text,
  `business_district_id` text,
  `vegetable_type` text,
  `less_than_forty` double DEFAULT NULL,
  `forty_eighty` double DEFAULT NULL,
  `forty_greathundred` double DEFAULT NULL,
  `greathundred_eightscore` double DEFAULT NULL,
  `greater_eightscore` double DEFAULT NULL,
  `batch` int(11) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private String vegetableType;
    private Double lessThanForty;
    private Double fortyEighty;
    private Double fortyGreathundred;
    private Double greathundredEightscore;
    private Double greaterEightscore;
    private Integer batch;
}
