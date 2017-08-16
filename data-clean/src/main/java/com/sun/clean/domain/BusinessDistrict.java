package com.sun.clean.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商圈表
 * @authur sunjian.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDistrict
{
    /*
      `id` char(32) NOT NULL,
      `name` varchar(50) DEFAULT NULL COMMENT '商圈名称',
      `primary_area` varchar(50) DEFAULT NULL,
      `secondary_area` varchar(50) DEFAULT NULL,
      `star_level` int(5) DEFAULT NULL,
      `labels` varchar(255) DEFAULT NULL,
      `open` tinyint(1) NOT NULL,
      `union_pay_code` varchar(10) DEFAULT NULL COMMENT '银联id',
     */
    private String id;
    private String name;
    private String primaryArea;
    private String secondaryArea;
    private Integer starLevel;
    private String labels;
    private boolean open;
    private String unionPayCode;
}
