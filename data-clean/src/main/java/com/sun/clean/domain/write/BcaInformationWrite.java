package com.sun.clean.domain.write;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @authur sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BcaInformationWrite
{
    /*
    `id` char(32) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `business_district_id` char(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `less_than_forty` bigint(20) DEFAULT NULL,
  `forty_eighty` bigint(20) DEFAULT NULL,
  `forty_greathundred` bigint(20) DEFAULT NULL,
  `greathundred_eightscore` bigint(20) DEFAULT NULL,
  `greater_eightscore` bigint(20) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private Long lesThanForty;
    private Long fortyEighty;
    private Long fortyGreathundred;
    private Long greathundredEightscore;
    private Long greaterEightscore;
    private Integer batch;
}
