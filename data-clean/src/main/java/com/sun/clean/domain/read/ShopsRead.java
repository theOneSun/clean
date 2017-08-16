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
public class ShopsRead
{
    /*
    `id` text,
  `business_district_id` text,
  `ca_shop_name` text,
  `ca_shop_styles` text,
  `ca_shop_avgprice` text,
  `ca_shop_reviews` text,
  `ca_shop_reviewers_group` int(11) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private String caShopName;
    private String caShopStyles;
    private String caShopAvgprice;
    private String caShopReviews;
    private Integer caShopReviewersGroup;
}
