package com.sun.clean.domain.write;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字段类型一致
 * @authur sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentShareWrite
{
    /*
    `id` char(32) NOT NULL,
  `business_district_id` char(32) DEFAULT NULL COMMENT '商圈ID',
  `ca_shop_styles` varchar(50) DEFAULT NULL COMMENT '菜系',
  `comment_growth_rate` double(10,5) DEFAULT NULL COMMENT '点评增长率',
  `ca_shop_reviewers_group` int(11) DEFAULT NULL COMMENT '批次'
     */
    private String id;
    private String businessDistrictId;
    private String caShopStyles;
    private Double reviewsPre;
    private Integer group;
}
