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
public class CommentWrite
{
    /*
    `id` char(32) CHARACTER SET utf8mb4 NOT NULL,
  `src_shop_id` char(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `des_shop_id` char(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL COMMENT '批次'
     */
    private String id;
    private String srcShopId;
    private String desShopId;
    private Integer count;
    private Integer batch;
}
