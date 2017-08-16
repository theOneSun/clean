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
public class CommentShareRead
{
    /*
    `id` text,
  `business_district_id` text,
  `ca_shop_styles` text,
  `reviews_pre` double DEFAULT NULL,
  `group` int(11) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private String caShopStyles;
    private Double reviewsPre;
    private Integer group;
}
