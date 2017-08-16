package com.sun.clean.domain.write;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @authur sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenderWrite
{
    /*
    `id` char(32) NOT NULL,
  `business_district_id` char(32) DEFAULT NULL COMMENT '商圈ID',
  `ca_persona_gender_male` varchar(10) DEFAULT NULL COMMENT '客户画像男性比例',
  `ca_persona_gender_female` varchar(10) DEFAULT NULL COMMENT '客户画像女性比例',
  `ca_persiona_gender_batch_index` varchar(255) DEFAULT NULL COMMENT '客户画像性别批次'
     */
    private String id;
    private String businessDistrictId;
    private String caPersonaGenderFemale;//先是女性比例,与read的顺序保持一致
    private String caPersonaGenderMale;
    private String caPersionaGenderBatchIndex;
}
