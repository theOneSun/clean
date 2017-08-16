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
public class AvgRatioRead
{
    private String id;
    private String businessDistrictId;
    private String caShopStyles;
    private Double reviewsPre;
    private Integer group;
}
