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
public class ShopsWrite
{
    /*
    `id` char(32) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `business_district_id` char(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ca_shop_name` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ca_shop_styles` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ca_shop_avgprice` double(15,5) DEFAULT NULL,
  `ca_shop_reviews` int(11) DEFAULT NULL,
  `ca_shop_reviewers_group` int(11) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private String caShopName;
    private String caShopStyles;
    private Double caShopAvgprice;
    private Integer caShopReviews;
    private Integer caShopReviewersGroup;
}
