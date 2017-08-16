package com.sun.clean.service.business.impl;

import com.sun.clean.constant.ErrorMessage;
import com.sun.clean.dao.mapper.read.AllReadMapper;
import com.sun.clean.dao.mapper.write.AgeWriteMapper;
import com.sun.clean.dao.mapper.write.AvgRatioWriteMapper;
import com.sun.clean.dao.mapper.write.AvgShopNumWriteMapper;
import com.sun.clean.dao.mapper.write.BcaIndexWriteMapper;
import com.sun.clean.dao.mapper.write.BcaInformationWriteMapper;
import com.sun.clean.dao.mapper.write.BcaSituationWriteMapper;
import com.sun.clean.dao.mapper.write.BusinessDistrictMapper;
import com.sun.clean.dao.mapper.write.BusinessUnionCodeMapper;
import com.sun.clean.dao.mapper.write.CommentDataWriteMapper;
import com.sun.clean.dao.mapper.write.CommentShareWriteMapper;
import com.sun.clean.dao.mapper.write.CommentWriteMapper;
import com.sun.clean.dao.mapper.write.EnvironmentMapper;
import com.sun.clean.dao.mapper.write.GenderWriteMapper;
import com.sun.clean.dao.mapper.write.MapCoordinateMapper;
import com.sun.clean.dao.mapper.write.RentalWriteMapper;
import com.sun.clean.dao.mapper.write.ShopsWriteMapper;
import com.sun.clean.domain.BusinessDistrict;
import com.sun.clean.domain.BusinessUnionCode;
import com.sun.clean.domain.read.BusinessRead;
import com.sun.clean.domain.vo.CheckToOpenVO;
import com.sun.clean.domain.vo.DispatchDataVO;
import com.sun.clean.domain.vo.InsertDataVO;
import com.sun.clean.domain.vo.InsertResult;
import com.sun.clean.domain.vo.RemainDataVO;
import com.sun.clean.domain.write.AgeWrite;
import com.sun.clean.domain.write.AvgRatioWrite;
import com.sun.clean.domain.write.AvgShopNumWrite;
import com.sun.clean.domain.write.BcaIndexWrite;
import com.sun.clean.domain.write.BcaInformationWrite;
import com.sun.clean.domain.write.BcaSituationWrite;
import com.sun.clean.domain.write.CommentDataWrite;
import com.sun.clean.domain.write.CommentShareWrite;
import com.sun.clean.domain.write.CommentWrite;
import com.sun.clean.domain.write.GenderWrite;
import com.sun.clean.domain.write.RentalWrite;
import com.sun.clean.domain.write.ShopsWrite;
import com.sun.clean.service.business.BusinessDistrictService;
import com.sun.clean.service.check.CommentCheckService;
import com.sun.clean.utils.OutputUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @authur sunjian.
 */
@Service
public class BusinessDistrictServiceImpl implements BusinessDistrictService
{

    @Resource
    private BusinessDistrictMapper businessDistrictMapper;

    @Resource
    private AllReadMapper allReadMapper;

    @Resource
    private BusinessUnionCodeMapper businessUnionCodeMapper;

    @Resource
    private AgeWriteMapper ageWriteMapper;

    @Resource
    private AvgRatioWriteMapper avgRatioWriteMapper;

    @Resource
    private AvgShopNumWriteMapper avgShopNumWriteMapper;

    @Resource
    private BcaIndexWriteMapper bcaIndexWriteMapper;

    @Resource
    private BcaInformationWriteMapper bcaInformationWriteMapper;

    @Resource
    private BcaSituationWriteMapper bcaSituationWriteMapper;

    @Resource
    private CommentWriteMapper commentWriteMapper;

    @Resource
    private CommentDataWriteMapper commentDataWriteMapper;

    @Resource
    private CommentShareWriteMapper commentShareWriteMapper;

    @Resource
    private GenderWriteMapper genderWriteMapper;

    @Resource
    private RentalWriteMapper rentalWriteMapper;

    @Resource
    private ShopsWriteMapper shopsWriteMapper;

    @Resource
    private CommentCheckService commentCheckService;

    @Resource
    private EnvironmentMapper environmentMapper;

    @Resource
    private MapCoordinateMapper mapCoordinateMapper;

    @Override
    public void openBusinessDistrict(String id)
    {
        businessDistrictMapper.setOpen(id);
    }

    @Override
    public List<BusinessDistrict> checkAndOpenDistrict(CheckToOpenVO checkToOpenVO) throws IOException
    {
        /*
        1,校验完整性
        2.通过后调用商圈开放校验的逻辑
            2.1找出共有的商圈
            2.2分离每个表的数据,分为可以开放商圈的数据和不可以开放商圈的数据
            2.3拿到全部可以开放的商圈对应的每张表的数据,
                2.3.1插入数据
                2.3.2设置商圈开放
         */

        //找出共有商圈
        List<Set<String>> businessIdSetList = checkToOpenVO.getBusinessDistrictIdSetList();
        Set<String> sameBusinessDistrictId = findBusinessDistrictId(businessIdSetList);
        if (sameBusinessDistrictId == null || sameBusinessDistrictId.size() == 0){
            return null;
        }
        //可以开放的商圈的id
        Set<String> openBusinessDistrictId = new HashSet<>();
        //校验Environment和MapCoordinate的是否有开放商圈的数据
        Set<String> businessIdFromEnvironment = environmentMapper.getAllBusinessDistrictId();
        Set<String> businessIdFromMapCoordinate = mapCoordinateMapper.getAllBusinessDistrictId();

        for (String id : sameBusinessDistrictId)
        {
            if (businessIdFromEnvironment.contains(id) && businessIdFromMapCoordinate.contains(id)){
                openBusinessDistrictId.add(id);
            }
        }

        //分离每个表的数据,分为可以开放商圈的数据和不可以开放商圈的数据(操作不含comment的)
        DispatchDataVO dispatchDataVO = dispatchData(checkToOpenVO, openBusinessDistrictId);

        //校验comment和shops关联关系
        dispatchDataVO = checkCommentAndShops(dispatchDataVO);

        //插入数据
        InsertResult insertResult = importAddData(dispatchDataVO.getInsertDataVO());
        System.out.println("insertResult : " + insertResult);
        //输出问题数据到文件
        OutputUtils.writeOpenBusinessDistrictToFile(dispatchDataVO);

        //设置商圈开放
        int openCount = businessDistrictMapper.setOpenBatch(openBusinessDistrictId);
        System.out.println("开放了   " + openCount + " 个商圈");

        return businessDistrictMapper.getList(openBusinessDistrictId);
    }

    /**
     * 校验comment和shops关联的关系
     * @param dispatchDataVO
     * @return
     */
    private DispatchDataVO checkCommentAndShops(DispatchDataVO dispatchDataVO){
        InsertDataVO insertDataVO = dispatchDataVO.getInsertDataVO();
        RemainDataVO remainDataVO = dispatchDataVO.getRemainDataVO();

        List<CommentWrite> commentImportList = insertDataVO.getCommentWriteList();
        List<ShopsWrite> shopsImportList = insertDataVO.getShopsWriteList();
        List<CommentWrite> commentRemainList = remainDataVO.getCommentWriteList();
        List<ShopsWrite> shopsRemainList = remainDataVO.getShopsWriteList();
        Map<String, List> resultMap = commentCheckService.checkForeignKey(commentImportList, shopsImportList);
        if (resultMap != null && resultMap.size() > 0){
            //说明数据有问题,要进行筛选
            if (resultMap.containsKey(ErrorMessage.COMMENT_CHECK_SHOPS_ID_NO_FOUND.getMessage())){
                //若存在shopsId在comment中的src查不到的,余留的加入,导入的删除
                List<ShopsWrite> shopsIdNotFoundInSrcList = resultMap.get(ErrorMessage.COMMENT_CHECK_SHOPS_ID_NO_FOUND.getMessage());
                shopsRemainList.addAll(shopsIdNotFoundInSrcList);
                shopsImportList.removeAll(shopsIdNotFoundInSrcList);
            }
            if (resultMap.containsKey(ErrorMessage.COMMENT_CHECK_DES_ID_NO_FOUND.getMessage())){
                //若存在desId在shops中查不到的,余留的加入,导入的删除
                List<CommentWrite> desIdNotFoundInShopsList = resultMap.get(ErrorMessage.COMMENT_CHECK_DES_ID_NO_FOUND.getMessage());
                commentRemainList.addAll(desIdNotFoundInShopsList);
                commentImportList.removeAll(desIdNotFoundInShopsList);
            }
        }
        return dispatchDataVO;
    }

    /**
     * 导入开放商圈需要的数据
     * @param insertDataVO 导入的数据
     * @return 返回每张表导入数据的数量
     */
    private InsertResult importAddData(InsertDataVO insertDataVO)
    {
        int ageCount = ageWriteMapper.insertList(insertDataVO.getAgeWriteList());
        int avgRatioCount = avgRatioWriteMapper.insertList(insertDataVO.getAvgRatioWriteList());
        int avgShopNumCount = avgShopNumWriteMapper.insertList(insertDataVO.getAvgShopNumWriteList());
        int bcaIndexCount = bcaIndexWriteMapper.insertList(insertDataVO.getBcaIndexWriteList());
        int bcaInformationCount = bcaInformationWriteMapper.insertList(insertDataVO.getBcaInformationWriteList());
        int bcaSituationCount = bcaSituationWriteMapper.insertList(insertDataVO.getBcaSituationWriteList());
        int commentCount = commentWriteMapper.insertList(insertDataVO.getCommentWriteList());
        int commentDataCount = commentDataWriteMapper.insertList(insertDataVO.getCommentDataWriteList());
        int commentShareCount = commentShareWriteMapper.insertList(insertDataVO.getCommentShareWriteList());
        int genderCount = genderWriteMapper.insertList(insertDataVO.getGenderWriteList());
        int rentalCount = rentalWriteMapper.insertList(insertDataVO.getRentalWriteList());
        int shopsCount = shopsWriteMapper.insertList(insertDataVO.getShopsWriteList());
        return new InsertResult(ageCount, avgRatioCount, avgShopNumCount, bcaIndexCount, bcaInformationCount, bcaSituationCount, commentCount, commentDataCount, commentShareCount, genderCount, rentalCount, shopsCount);
    }

    /**
     * 分离数据
     * @param checkToOpenVO
     * @param openBusinessDistrictId
     * @return
     */
    private DispatchDataVO dispatchData(CheckToOpenVO checkToOpenVO, Set<String> openBusinessDistrictId){
        DispatchDataVO dispatchDataVO = new DispatchDataVO();
        InsertDataVO insertDataVO = new InsertDataVO();
        RemainDataVO remainDataVO = new RemainDataVO();
        dispatchDataVO.setInsertDataVO(insertDataVO);
        dispatchDataVO.setRemainDataVO(remainDataVO);

        //--------------开始分离---------------
        dispatchDataVO = dispatchAgeData(checkToOpenVO.getAgeCheckResult().getRedundantList(), openBusinessDistrictId, dispatchDataVO);
        dispatchDataVO = dispatchAvgRatioData(checkToOpenVO.getAvgRatioCheckResult().getRedundantList(), openBusinessDistrictId, dispatchDataVO);
        dispatchDataVO = dispatchAvgShopNumData(checkToOpenVO.getAvgShopNumCheckResult().getRedundantList(), openBusinessDistrictId, dispatchDataVO);
        dispatchDataVO = dispatchBcaIndexData(checkToOpenVO.getBcaIndexCheckResult().getRedundantList(), openBusinessDistrictId, dispatchDataVO);
        dispatchDataVO = dispatchBcaInformationData(checkToOpenVO.getBcaInformationCheckResult().getRedundantList(), openBusinessDistrictId, dispatchDataVO);
        dispatchDataVO = dispatchBcaSituationData(checkToOpenVO.getBcaSituationCheckResult().getRedundantList(), openBusinessDistrictId, dispatchDataVO);
        dispatchDataVO = dispatchCommentDataData(checkToOpenVO.getCommentDataCheckResult().getRedundantList(), openBusinessDistrictId, dispatchDataVO);
        dispatchDataVO = dispatchCommentShareData(checkToOpenVO.getCommentShareCheckResult().getRedundantList(), openBusinessDistrictId, dispatchDataVO);
        dispatchDataVO = dispatchGenderData(checkToOpenVO.getGenderCheckResult().getRedundantList(), openBusinessDistrictId, dispatchDataVO);
        dispatchDataVO = dispatchRenTalData(checkToOpenVO.getRentalCheckResult().getRedundantList(), openBusinessDistrictId, dispatchDataVO);
        dispatchDataVO = dispatchShopsData(checkToOpenVO.getShopsCheckResult().getRedundantList(), openBusinessDistrictId, dispatchDataVO);
//        dispatchDataVO = dispatchCommentData(checkToOpenVO.getCommentCheckResult(), dispatchDataVO);
        //校验comment和shops关联关系
        dispatchDataVO = checkCommentAndShops(dispatchDataVO);
        return dispatchDataVO;
    }

    /**
     * 找出每个结果中共有的商圈
     *
     * @param businessIdSetList
     * @return
     */
    private Set<String> findBusinessDistrictId(List<Set<String>> businessIdSetList)
    {
        if (businessIdSetList == null || businessIdSetList.size() != 11)
        {
            System.out.println("参数findIdCheckResultList有问题");
            return null;
        }

        Set<String> templateSet = businessIdSetList.get(0);
        Set<String> compareSet;

        for (int i = 1; i < 11; i++)
        {
            compareSet = businessIdSetList.get(i);
            //找出相同的businessId,使用已找出共同的和新的比较取出共同的
            templateSet = findSameId(templateSet, compareSet);
        }

        return templateSet;
    }

    /**
     * 两个set比较取出相同商圈id
     *
     * @param idSet1
     * @param idSet2
     * @return 返回相同的商圈IDset
     */
    private Set<String> findSameId(Set<String> idSet1, Set<String> idSet2)
    {
        Set<String> sameSet = new HashSet<>();
        for (String id : idSet1)
        {
            for (String id2 : idSet2)
            {
                if (id.equals(id2))
                {
                    sameSet.add(id);
                }
            }
        }
        return sameSet;
    }

    /**
     * 分离age数据
     * @param redundantList
     * @param openBusinessDistrictId
     * @param dispatchDataVO
     * @return
     */
    private DispatchDataVO dispatchAgeData(List<AgeWrite> redundantList, Set<String> openBusinessDistrictId,
                                           DispatchDataVO dispatchDataVO)
    {
        List<AgeWrite> ageInsertList = new ArrayList<>();
        List<AgeWrite> ageRemainList = new ArrayList<>();
        InsertDataVO insertDataVO = dispatchDataVO.getInsertDataVO();
        RemainDataVO remainDataVO = dispatchDataVO.getRemainDataVO();
        for (AgeWrite ageWrite : redundantList)
        {
            if (openBusinessDistrictId.contains(ageWrite.getBusinessDistrictId())){
                ageInsertList.add(ageWrite);
            }else {
                ageRemainList.add(ageWrite);
            }
        }
        if (ageInsertList.size() > 0){
            insertDataVO.setAgeWriteList(ageInsertList);
        }
        if (ageRemainList.size() > 0){
            remainDataVO.setAgeWriteList(ageRemainList);
        }
        return dispatchDataVO;
    }

    /**
     * 分离avgRatio的数据
     * @param redundantList
     * @param openBusinessDistrictId
     * @param dispatchDataVO
     * @return
     */
    private DispatchDataVO dispatchAvgRatioData(List<AvgRatioWrite> redundantList, Set<String> openBusinessDistrictId,
                                                DispatchDataVO dispatchDataVO)
    {
        List<AvgRatioWrite> insertList = new ArrayList<>();
        List<AvgRatioWrite> remainList = new ArrayList<>();
        InsertDataVO insertDataVO = dispatchDataVO.getInsertDataVO();
        RemainDataVO remainDataVO = dispatchDataVO.getRemainDataVO();
        for (AvgRatioWrite write : redundantList)
        {
            if (openBusinessDistrictId.contains(write.getBusinessDistrictId())){
                insertList.add(write);
            }else {
                remainList.add(write);
            }
        }
        if (insertList.size() > 0){
            insertDataVO.setAvgRatioWriteList(insertList);
        }
        if (remainList.size() > 0){
            remainDataVO.setAvgRatioWriteList(remainList);
        }
        return dispatchDataVO;
    }

    /**
     * 分离avgShopNum数据
     * @param redundantList
     * @param openBusinessDistrictId
     * @param dispatchDataVO
     * @return
     */
    private DispatchDataVO dispatchAvgShopNumData(List<AvgShopNumWrite> redundantList, Set<String> openBusinessDistrictId,
                                                  DispatchDataVO dispatchDataVO)
    {
        List<AvgShopNumWrite> insertList = new ArrayList<>();
        List<AvgShopNumWrite> remainList = new ArrayList<>();
        InsertDataVO insertDataVO = dispatchDataVO.getInsertDataVO();
        RemainDataVO remainDataVO = dispatchDataVO.getRemainDataVO();
        for (AvgShopNumWrite write : redundantList)
        {
            if (openBusinessDistrictId.contains(write.getBusinessDistrictId())){
                insertList.add(write);
            }else {
                remainList.add(write);
            }
        }
        if (insertList.size() > 0){
            insertDataVO.setAvgShopNumWriteList(insertList);
        }
        if (remainList.size() > 0){
            remainDataVO.setAvgShopNumWriteList(remainList);
        }
        return dispatchDataVO;
    }

    /**
     * 分离bcaIndex的数据
     * @param redundantList
     * @param openBusinessDistrictId
     * @param dispatchDataVO
     * @return
     */
    private DispatchDataVO dispatchBcaIndexData(List<BcaIndexWrite> redundantList, Set<String> openBusinessDistrictId,
                                                DispatchDataVO dispatchDataVO)
    {
        List<BcaIndexWrite> insertList = new ArrayList<>();
        List<BcaIndexWrite> remainList = new ArrayList<>();
        InsertDataVO insertDataVO = dispatchDataVO.getInsertDataVO();
        RemainDataVO remainDataVO = dispatchDataVO.getRemainDataVO();
        for (BcaIndexWrite write : redundantList)
        {
            if (openBusinessDistrictId.contains(write.getBusinessDistrictId())){
                insertList.add(write);
            }else {
                remainList.add(write);
            }
        }
        if (insertList.size() > 0){
            insertDataVO.setBcaIndexWriteList(insertList);
        }
        if (remainList.size() > 0){
            remainDataVO.setBcaIndexWriteList(remainList);
        }
        return dispatchDataVO;
    }

    /**
     * 分离bcaInformation的数据
     * @param redundantList
     * @param openBusinessDistrictId
     * @param dispatchDataVO
     * @return
     */
    private DispatchDataVO dispatchBcaInformationData(List<BcaInformationWrite> redundantList, Set<String> openBusinessDistrictId,
                                                      DispatchDataVO dispatchDataVO)
    {
        List<BcaInformationWrite> insertList = new ArrayList<>();
        List<BcaInformationWrite> remainList = new ArrayList<>();
        InsertDataVO insertDataVO = dispatchDataVO.getInsertDataVO();
        RemainDataVO remainDataVO = dispatchDataVO.getRemainDataVO();
        for (BcaInformationWrite write : redundantList)
        {
            if (openBusinessDistrictId.contains(write.getBusinessDistrictId())){
                insertList.add(write);
            }else {
                remainList.add(write);
            }
        }
        if (insertList.size() > 0){
            insertDataVO.setBcaInformationWriteList(insertList);
        }
        if (remainList.size() > 0){
            remainDataVO.setBcaInformationWriteList(remainList);
        }
        return dispatchDataVO;
    }


    /**
     * 分离bcaSituation的数据
     * @param redundantList
     * @param openBusinessDistrictId
     * @param dispatchDataVO
     * @return
     */
    private DispatchDataVO dispatchBcaSituationData(List<BcaSituationWrite> redundantList, Set<String> openBusinessDistrictId,
                                                    DispatchDataVO dispatchDataVO)
    {
        List<BcaSituationWrite> insertList = new ArrayList<>();
        List<BcaSituationWrite> remainList = new ArrayList<>();
        InsertDataVO insertDataVO = dispatchDataVO.getInsertDataVO();
        RemainDataVO remainDataVO = dispatchDataVO.getRemainDataVO();
        for (BcaSituationWrite write : redundantList)
        {
            if (openBusinessDistrictId.contains(write.getBusinessDistrictId())){
                insertList.add(write);
            }else {
                remainList.add(write);
            }
        }
        if (insertList.size() > 0){
            insertDataVO.setBcaSituationWriteList(insertList);
        }
        if (remainList.size() > 0){
            remainDataVO.setBcaSituationWriteList(remainList);
        }
        return dispatchDataVO;
    }

    /**
     * 分离commentData的数据
     * @param redundantList
     * @param openBusinessDistrictId
     * @param dispatchDataVO
     * @return
     */
    private DispatchDataVO dispatchCommentDataData(List<CommentDataWrite> redundantList, Set<String> openBusinessDistrictId,
                                                   DispatchDataVO dispatchDataVO)
    {
        List<CommentDataWrite> insertList = new ArrayList<>();
        List<CommentDataWrite> remainList = new ArrayList<>();
        InsertDataVO insertDataVO = dispatchDataVO.getInsertDataVO();
        RemainDataVO remainDataVO = dispatchDataVO.getRemainDataVO();
        for (CommentDataWrite write : redundantList)
        {
            if (openBusinessDistrictId.contains(write.getBusinessDistrictId())){
                insertList.add(write);
            }else {
                remainList.add(write);
            }
        }
        if (insertList.size() > 0){
            insertDataVO.setCommentDataWriteList(insertList);
        }
        if (remainList.size() > 0){
            remainDataVO.setCommentDataWriteList(remainList);
        }
        return dispatchDataVO;
    }


    /**
     * 分离commentShare的数据
     * @param redundantList
     * @param openBusinessDistrictId
     * @param dispatchDataVO
     * @return
     */
    private DispatchDataVO dispatchCommentShareData(List<CommentShareWrite> redundantList, Set<String> openBusinessDistrictId,
                                                    DispatchDataVO dispatchDataVO)
    {
        List<CommentShareWrite> insertList = new ArrayList<>();
        List<CommentShareWrite> remainList = new ArrayList<>();
        InsertDataVO insertDataVO = dispatchDataVO.getInsertDataVO();
        RemainDataVO remainDataVO = dispatchDataVO.getRemainDataVO();
        for (CommentShareWrite write : redundantList)
        {
            if (openBusinessDistrictId.contains(write.getBusinessDistrictId())){
                insertList.add(write);
            }else {
                remainList.add(write);
            }
        }
        if (insertList.size() > 0){
            insertDataVO.setCommentShareWriteList(insertList);
        }
        if (remainList.size() > 0){
            remainDataVO.setCommentShareWriteList(remainList);
        }
        return dispatchDataVO;
    }

    /**
     * 分离avgRatio的数据
     * @param redundantList
     * @param openBusinessDistrictId
     * @param dispatchDataVO
     * @return
     */
    private DispatchDataVO dispatchGenderData(List<GenderWrite> redundantList, Set<String> openBusinessDistrictId,
                                              DispatchDataVO dispatchDataVO)
    {
        List<GenderWrite> insertList = new ArrayList<>();
        List<GenderWrite> remainList = new ArrayList<>();
        InsertDataVO insertDataVO = dispatchDataVO.getInsertDataVO();
        RemainDataVO remainDataVO = dispatchDataVO.getRemainDataVO();
        for (GenderWrite write : redundantList)
        {
            if (openBusinessDistrictId.contains(write.getBusinessDistrictId())){
                insertList.add(write);
            }else {
                remainList.add(write);
            }
        }
        if (insertList.size() > 0){
            insertDataVO.setGenderWriteList(insertList);
        }
        if (remainList.size() > 0){
            remainDataVO.setGenderWriteList(remainList);
        }
        return dispatchDataVO;
    }


    /**
     * 分离rental的数据
     * @param redundantList
     * @param openBusinessDistrictId
     * @param dispatchDataVO
     * @return
     */
    private DispatchDataVO dispatchRenTalData(List<RentalWrite> redundantList, Set<String> openBusinessDistrictId,
                                              DispatchDataVO dispatchDataVO)
    {
        List<RentalWrite> insertList = new ArrayList<>();
        List<RentalWrite> remainList = new ArrayList<>();
        InsertDataVO insertDataVO = dispatchDataVO.getInsertDataVO();
        RemainDataVO remainDataVO = dispatchDataVO.getRemainDataVO();
        for (RentalWrite write : redundantList)
        {
            if (openBusinessDistrictId.contains(write.getBusinessDistrictId())){
                insertList.add(write);
            }else {
                remainList.add(write);
            }
        }
        if (insertList.size() > 0){
            insertDataVO.setRentalWriteList(insertList);
        }
        if (remainList.size() > 0){
            remainDataVO.setRentalWriteList(remainList);
        }
        return dispatchDataVO;
    }

    /**
     * 分离avgRatio的数据
     * @param redundantList
     * @param openBusinessDistrictId
     * @param dispatchDataVO
     * @return
     */
    private DispatchDataVO dispatchShopsData(List<ShopsWrite> redundantList, Set<String> openBusinessDistrictId,
                                             DispatchDataVO dispatchDataVO)
    {
        List<ShopsWrite> insertList = new ArrayList<>();
        List<ShopsWrite> remainList = new ArrayList<>();
        InsertDataVO insertDataVO = dispatchDataVO.getInsertDataVO();
        RemainDataVO remainDataVO = dispatchDataVO.getRemainDataVO();
        for (ShopsWrite write : redundantList)
        {
            if (openBusinessDistrictId.contains(write.getBusinessDistrictId())){
                insertList.add(write);
            }else {
                remainList.add(write);
            }
        }
        if (insertList.size() > 0){
            insertDataVO.setShopsWriteList(insertList);
        }
        if (remainList.size() > 0){
            remainDataVO.setShopsWriteList(remainList);
        }
        return dispatchDataVO;
    }

    @Override
    public int checkAndImport()
    {
        /*
        1.读取元数据表的商圈,获取城市,区域,商圈,id
            1.1遍历,判断已有的是否包含,包含则跳过
            1.2不包含封装对象
        2.读取temp,查询对应的银联code
        3.封装商圈对象
         */
        List<BusinessRead> businessReadList = allReadMapper.getAllBusinessRead();
        List<BusinessUnionCode> businessUnionCodeList = businessUnionCodeMapper.getAll();
        Set<String> existIdSet = businessDistrictMapper.getAllId();
        BusinessDistrict businessDistrict;
        List<BusinessDistrict> insertList = new ArrayList<>();
        //-----------------------------------------------------------------
        //将商圈code存储到map中
        Map<String,String> codeMap = new HashMap<>();
        for (BusinessUnionCode businessUnionCode:businessUnionCodeList){
            codeMap.put(businessUnionCode.getCity()+businessUnionCode.getBusiness(),businessUnionCode.getUnionCode());
        }

        String region;
        String business;

        for (BusinessRead businessRead : businessReadList)
        {
            String uuid = businessRead.getUuid();
            if (!existIdSet.contains(uuid))
            {
                region = businessRead.getRegin();
                business = businessRead.getBusiness();
                String unionCode = codeMap.get(region + business);
                businessDistrict = new BusinessDistrict(businessRead.getUuid(), business, region, businessRead.getAdministrativeRegion(), null, null, false, unionCode);
                insertList.add(businessDistrict);
            }
        }
        if (insertList.size() != 0)
        {
            return businessDistrictMapper.insertList(insertList);
        } else
        {
            return 0;
        }
    }
}
