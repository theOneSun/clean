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
public class RentalRead
{
    /*
    `id` text,
  `business_district_id` text,
  `ca_name` text,
  `ca_rental_street` double DEFAULT NULL,
  `ca_rental_office_building` double DEFAULT NULL,
  `ca_rental_residence` double DEFAULT NULL,
  `ca_rental_average` double DEFAULT NULL,
  `batch` int(11) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private String caName;
    private Double caRentalStreet;
    private Double caRentalOfficeBuilding;
    private Double caRentalResidence;
    private Double caRentalAverage;
    private Integer batch;

}
