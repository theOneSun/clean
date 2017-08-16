package com.sun.clean.domain.write;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @authur sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalWrite
{
    /*
    `id` char(32) NOT NULL,
  `business_district_id` char(32) DEFAULT NULL COMMENT '商圈ID',
  `ca_name` char(32) DEFAULT NULL COMMENT '商圈名称',
  `ca_rental_street` double(10,2) DEFAULT NULL COMMENT '商业街租金',
  `ca_rental_office_building` double(10,2) DEFAULT NULL COMMENT '写字楼租金',
  `ca_rental_residence` double(10,2) DEFAULT NULL COMMENT '住宅底商租金',
  `ca_rental_average` double(10,2) DEFAULT NULL COMMENT '平均租金',
  `ca_rental_batch_index` varchar(255) DEFAULT NULL COMMENT '商圈租金批次'
     */
    private String id;
    private String businessDistrictId;
    private String caName;
    private Double caRentalStreet;
    private Double caRentalOfficeBuilding;
    private Double caRentalResidence;
    private Double caRentalAverage;
    private String caRentalBatchIndex;

}
