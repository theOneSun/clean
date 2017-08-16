package com.sun.clean.domain.write;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 此类与对应的read类字段类型一致
 * @authur sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDataWrite
{
    /*
    `id` char(32) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `business_district_id` char(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '商圈名称',
  `vegetable_type` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '菜系',
  `less_than_forty` double DEFAULT NULL COMMENT '小于40',
  `forty_eighty` double DEFAULT NULL COMMENT '40~80',
  `forty_greathundred` double DEFAULT NULL COMMENT '80~120',
  `greathundred_eightscore` double DEFAULT NULL COMMENT '120~160',
  `greater_eightscore` double DEFAULT NULL COMMENT '160+',
  `batch` int(11) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private String vegetableType;
    private Double lessThanForty;
    private Double fortyEighty;
    private Double fortyGreathundred;
    private Double greathundredEightscore;
    private Double greaterEightscore;
    private Integer batch;
}
