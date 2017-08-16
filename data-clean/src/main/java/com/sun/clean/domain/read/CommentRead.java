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
public class CommentRead
{
    /*
`id` text,
  `src_shop_id` text,
  `des_shop_id` text,
  `count` text,
  `batch` int(11) DEFAULT NULL
     */
    private String id;
    private String srcShopId;
    private String desShopId;
    private String count;
    private Integer batch;
}
