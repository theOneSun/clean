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
public class Environment
{
    /*
    `id` char(32) NOT NULL COMMENT '商圈环境id',
  `business_district_id` varchar(32) DEFAULT NULL,
  `trading_area` varchar(32) DEFAULT NULL COMMENT '商圈名称',
  `shopping_mall` int(32) DEFAULT NULL COMMENT '购物中心',
  `residential_quarters` int(32) DEFAULT NULL COMMENT '小区',
  `school` int(32) DEFAULT NULL COMMENT '学校',
  `hotel` int(32) DEFAULT NULL COMMENT '酒店',
  `office_building` int(32) DEFAULT NULL COMMENT '写字楼',
  `subway_station` int(32) DEFAULT NULL COMMENT '地铁站',
  `group` int(11) DEFAULT NULL COMMENT '数据批次',
     */
    private String id;
    private String businessDistrictId;
    private String tradingArea;
    private Integer shoppingMall;
    private Integer residentialQuarters;
    private Integer school;
    private Integer hotel;
    private Integer officeBuilding;
    private Integer subwayStation;
    private Integer group;
}
