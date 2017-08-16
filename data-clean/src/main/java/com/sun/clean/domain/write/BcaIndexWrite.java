package com.sun.clean.domain.write;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @authur sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BcaIndexWrite
{
    /*
    `id` char(32) NOT NULL COMMENT '分时段流量指数id',
  `date` datetime DEFAULT NULL COMMENT '日期',
  `shunt_index` double(32,2) DEFAULT NULL COMMENT '分流时段指数',
  `business_district_id` varchar(32) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private Timestamp date;
    private Double shuntIndex;
}
