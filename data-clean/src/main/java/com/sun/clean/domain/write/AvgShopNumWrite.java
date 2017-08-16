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
public class AvgShopNumWrite
{
    /*
     `id` char(32) CHARACTER SET utf8mb4 NOT NULL,
  `business_district_id` char(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ca_shop_styles` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `comment_growth_rate` double(10,5) DEFAULT NULL,
  `ca_shop_reviewers_group` int(11) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private String caShopStyles;
    private Double commentGrowthRate;
    private Integer caShopReviewersGroup;

}
