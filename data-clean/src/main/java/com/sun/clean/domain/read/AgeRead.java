package com.sun.clean.domain.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @authur sunjian.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgeRead
{
    private String id; //商圈ID
    private String businessDistrictId; //客户画像0-19年龄段
    private Double caPersonaAge_0_19; //客户画像20-29年龄段
    private Double caPersonaAge_20_29; //客户画像20-29年龄段
    private Double caPersonaAge_30_39; //客户画像30-39年龄段
    private Double caPersonaAge_40_49; //客户画像40-49年龄段
    private Double caPersonaAge_50_59; //客户画像50-59年龄段
    private Double caPersonaAge_60_above; //客户画像60+年龄段
    private Integer batch; //客户画像年龄批次
}
