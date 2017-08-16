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
public class BusinessRead
{
    /*
    `uuid` text,
  `reginCode` int(11) DEFAULT NULL,
  `regin` text,
  `administrativeRegionCode` text,
  `administrativeRegion` text,
  `businessCode` text,
  `business` text
     */
    private String uuid;
    private Integer reginCode;
    private String regin;
    private String administrativeRegionCode;
    private String administrativeRegion;
    private String businessCode;
    private String business;
}
