package com.sun.clean.domain.write;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 与读的类的字段的类型均一致
 * @authur sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BcaSituationWrite
{
    /*
    `id` char(32) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `business_district_id` char(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `meal_type` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `number` bigint(20) NOT NULL,
  `group` int(11) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private String mealType;
    private Long number;
    private Integer group;
}
