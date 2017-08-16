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
public class BcaIndexRead
{
    /*
     `id` text,
  `business_district_id` text,
  `date` text,
  `shunt_index` int(11) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private String date;
    private Integer shuntIndex;
}
