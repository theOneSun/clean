package com.sun.clean.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @authur sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapCoordinate
{
    /*
    `id` char(32) NOT NULL,
  `trading_area` varchar(32) DEFAULT NULL COMMENT '商圈',
  `coordinate` text COMMENT '坐标',
  `business_district_id` varchar(32) DEFAULT NULL,
     */
    private String id;
    private String tradingArea;
    private String coordinate;
    private String businessDistrictId;
}
