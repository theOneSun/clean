package com.sun.clean.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @authur sunjian.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenResult<T>
{
    /**
     * 开放商圈插入的数据
     */
    private List<T> openList;

    /**
     * 无法开放的商圈对应的多余数据
     */
    private List<T> redundantList;
}
