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
public class BcaInformationRead
{
    /*
    `id` text,
  `business_district_id` text,
  `less_than_forty` bigint(20) DEFAULT NULL,
  `forty_eighty` bigint(20) DEFAULT NULL,
  `forty_greathundred` bigint(20) DEFAULT NULL,
  `greathundred_eightscore` bigint(20) DEFAULT NULL,
  `greater_eightscore` bigint(20) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private Long lessThanForty;
    private Long fortyEighty;
    private Long fortyGreathundred;
    private Long greathundredEightscore;
    private Long greaterEightscore;
    private Integer batch;
}
