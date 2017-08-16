package com.sun.clean.service.business;

import com.sun.clean.domain.BusinessDistrict;
import com.sun.clean.domain.vo.CheckToOpenVO;

import java.io.IOException;
import java.util.List;

/**
 * @authur sunjian.
 */
public interface BusinessDistrictService
{
//    List checkLackBusinessDistrict(List);


    /**
     * 开放商圈
     *
     * @param id 商圈id
     */
    void openBusinessDistrict(String id);

    /**
     * 开放商圈前的校验
     * @param checkToOpenVO 数据校验的结果
     * @return 可以开放商圈的id集合
     */
    List<BusinessDistrict> checkAndOpenDistrict(CheckToOpenVO checkToOpenVO) throws IOException;

    /**
     * 校验导入商圈
     * @return
     */
    int checkAndImport();
}
