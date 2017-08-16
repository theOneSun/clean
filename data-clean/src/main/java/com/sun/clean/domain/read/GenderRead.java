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
public class GenderRead
{
    /*
    `id` text,
  `business_district_id` text,
  `ca_persona_gender_female` double DEFAULT NULL,
  `ca_persona_gender_male` double DEFAULT NULL,
  `batch` int(11) DEFAULT NULL
     */
    private String id;
    private String businessDistrictId;
    private Double caPersonaGenderFemale;
    private Double caPersonaGenderMale;
    private Integer batch;
}
