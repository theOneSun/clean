package com.sun.clean.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @authur sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUnionCode
{
    /*
    `city` varchar(10) NOT NULL,
  `business` varchar(100) NOT NULL,
  `union_code` varchar(255) NOT NULL
     */
    private String city;
    private String business;
    private String unionCode;
}
