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
public class AvgShopNumRead
{
    /*
    `id` text,
  `business_district_id` text,
  `meal_type` text,
  `number` bigint(20) NOT NULL,
  `group` int(11) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private String mealType;
    private Long number;
    private Integer group;
}
