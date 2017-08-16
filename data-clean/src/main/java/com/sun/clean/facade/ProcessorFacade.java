package com.sun.clean.facade;

import com.sun.clean.config.TargetTable;
import com.sun.clean.constant.TableSimpleName;
import com.sun.clean.domain.BusinessDistrict;
import com.sun.clean.domain.vo.CheckResult;
import com.sun.clean.domain.vo.CheckToOpenVO;
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
import com.sun.clean.processor.AgeProcessor;
import com.sun.clean.processor.AvgRatioProcessor;
import com.sun.clean.processor.AvgShopNumProcessor;
import com.sun.clean.processor.Processor;
import com.sun.clean.service.business.BusinessDistrictService;
import com.sun.clean.utils.OutputUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @authur sunjian.
 */
@Service
public class ProcessorFacade
{
    @Resource
    private AgeProcessor ageProcessor;
    @Resource
    private AvgRatioProcessor avgRatioProcessor;
    @Resource
    private AvgShopNumProcessor avgShopNumProcessor;
    /*@Resource
    private BcaIndexProcessor bcaIndexProcessor;
    @Resource
    private BcaInformationProcessor bcaInformationProcessor;
    @Resource
    private BcaSituationProcessor bcaSituationProcessor;
    @Resource
    private CommentProcessor commentProcessor;
    @Resource
    private CommentDataProcessor commentDataProcessor;
    @Resource
    private CommentShareProcessor commentShareProcessor;
    @Resource
    private GenderProcessor genderProcessor;
    @Resource
    private RentalProcessor rentalProcessor;
    @Resource
    private ShopsProcessor shopsProcessor;
    @Resource
    private BusinessService businessService;*/
    @Resource
    private TargetTable targetTable;
    @Resource
    private BusinessDistrictService businessDistrictService;

    private CheckResult<AgeWrite> ageWriteCheckResult;
    private CheckResult<AvgRatioWrite> avgRatioWriteCheckResult;
    private CheckResult<AvgShopNumWrite> avgShopNumWriteCheckResult;
    private CheckResult<BcaIndexWrite> bcaIndexWriteCheckResult;
    private CheckResult<BcaInformationWrite> bcaInformationWriteCheckResult;
    private CheckResult<BcaSituationWrite> bcaSituationWriteCheckResult;
    private CheckResult<CommentWrite> commentWriteCheckResult;
    private CheckResult<CommentDataWrite> commentDataWriteCheckResult;
    private CheckResult<CommentShareWrite> commentShareWriteCheckResult;
    private CheckResult<GenderWrite> genderWriteCheckResult;
    private CheckResult<RentalWrite> rentalWriteCheckResult;
    private CheckResult<ShopsWrite> shopsWriteCheckResult;


    /**
     * 核心main方法
     *
     * @throws Exception
     */
    public void checkDataAndImport() throws Exception
    {
        CheckToOpenVO checkToOpenVO = checkData();//返回可以开放商圈的checkResultVO
        List<String> nameList = targetTable.getNames();
        if (nameList.size() == 12)
        {
            //说明全部表进行了更新
            List<Set<String>> businessDistrictIdSetList = checkToOpenVO.getBusinessDistrictIdSetList();
            CheckResult<CommentWrite> commentCheckResult = checkToOpenVO.getCommentCheckResult();
            if (businessDistrictIdSetList != null && businessDistrictIdSetList.size() == 11 && commentCheckResult != null)
            {
                //满足开放商圈的硬性需求
                List<BusinessDistrict> openBusinessDistrictList = businessDistrictService.checkAndOpenDistrict(checkToOpenVO);
                System.out.println("开放了:  " + (openBusinessDistrictList == null ? "0" : openBusinessDistrictList.size()) + "个商圈");
            }
        } else
        {
            //仅导入数据
            importCheckResultData();
        }
    }


    /**
     * 导入数据(根据checkResult)
     */
    private void importCheckResultData() throws Exception
    {
        List<String> nameList = targetTable.getNames();
        for (String name : nameList)
        {
            if (TableSimpleName.TABLE_AGE.equals(name))
            {
                int i = importData(ageProcessor, ageWriteCheckResult);
                System.out.println("age导入了" + i + "条数据");
                OutputUtils.printCheckResultToFile(name, ageWriteCheckResult);
            }
            if (TableSimpleName.TABLE_AVG_RATIO.equals(name))
            {
                int i = importData(avgRatioProcessor, avgRatioWriteCheckResult);
                System.out.println("avgRatio导入了" + i + "条数据");
                OutputUtils.printCheckResultToFile(name, avgRatioWriteCheckResult);
            }
            if (TableSimpleName.TABLE_AVG_SHOP_NUM.equals(name))
            {
                int i = importData(avgShopNumProcessor, avgShopNumWriteCheckResult);
                System.out.println("avgShopNum导入了" + i + "条数据");
                OutputUtils.printCheckResultToFile(name, avgShopNumWriteCheckResult);
            }
            // TODO: 补充完其余表的导入
        }
    }

    /**
     * 导入数据(基本类)
     * @param processor 处理器
     * @param checkResult 校验结果
     * @param <T> 返回结果泛型
     * @param <U> 处理器读取数据的泛型
     * @return
     */
    private <T, U> int importData(Processor<T, U> processor, CheckResult<T> checkResult)
    {
        List<T> importDataList = checkResult.getImportDataList();
        int importCount = 0;
        if (importDataList != null && importDataList.size() > 0)
        {
            importCount = processor.importData(importDataList);
        }
        return importCount;
    }

    /*private <T> void importData(CheckResult<T> checkResult, String table) throws Exception {
        List<T> importDataList = checkResult.getImportDataList();
        OutputUtils.printCheckResultToFile(table,checkResult);
    }*/

    /**
     * 校验是否可以进行开放商圈逻辑(校验所有数据的完整性)
     *
     * @return
     * @throws Exception
     */
    private CheckToOpenVO checkData() throws Exception
    {

        List<String> nameList = targetTable.getNames();

        List<Set<String>> businessIdSetList = new ArrayList<>();

        //传递参数的vo
        CheckToOpenVO checkToOpenVO = new CheckToOpenVO();

        for (String target : nameList)
        {
            System.out.println(target);
            if (TableSimpleName.TABLE_AGE.equals(target))
            {
                this.ageWriteCheckResult = ageProcessor.process();

                if (judgeAdd(ageWriteCheckResult))
                {
                    checkToOpenVO.setAgeCheckResult(ageWriteCheckResult);
                    Set<String> businessIdSet = new HashSet<>();
                    ageWriteCheckResult.getRedundantList()
                                       .stream()
                                       .distinct()
                                       .forEach(ageWrite -> businessIdSet.add(ageWrite.getBusinessDistrictId()));
                    businessIdSetList.add(businessIdSet);
                }
            }
            if (TableSimpleName.TABLE_AVG_RATIO.equals(target))
            {
                this.avgRatioWriteCheckResult = avgRatioProcessor.process();
                if (judgeAdd(avgRatioWriteCheckResult))
                {
                    checkToOpenVO.setAvgRatioCheckResult(avgRatioWriteCheckResult);
                    Set<String> businessIdSet = new HashSet<>();
                    avgRatioWriteCheckResult.getRedundantList()
                                            .stream()
                                            .distinct()
                                            .forEach(avgRatioWrite -> businessIdSet.add(avgRatioWrite.getBusinessDistrictId()));
                    businessIdSetList.add(businessIdSet);
                }
            }
            if (TableSimpleName.TABLE_AVG_SHOP_NUM.equals(target))
            {
                this.avgShopNumWriteCheckResult = avgShopNumProcessor.process();
                if (judgeAdd(avgShopNumWriteCheckResult))
                {
                    checkToOpenVO.setAvgShopNumCheckResult(avgShopNumWriteCheckResult);

                    Set<String> businessIdSet = new HashSet<>();
                    avgShopNumWriteCheckResult.getRedundantList()
                                              .stream()
                                              .distinct()
                                              .forEach(avgShopNumWrite -> businessIdSet.add(avgShopNumWrite.getBusinessDistrictId()));
                    businessIdSetList.add(businessIdSet);
                }
            }
            /*if (TableSimpleName.TABLE_BCA_INDEX.equals(target))
            {
                this.bcaIndexWriteCheckResult = bcaIndexProcessor.process();

                if (judgeAdd(bcaIndexWriteCheckResult))
                {
                    checkToOpenVO.setBcaIndexCheckResult(bcaIndexWriteCheckResult);
                    Set<String> businessIdSet = new HashSet<>();
                    bcaIndexWriteCheckResult.getRedundantList()
                                            .stream()
                                            .distinct()
                                            .forEach(bcaIndexWrite -> businessIdSet.add(bcaIndexWrite.getBusinessDistrictId()));
                    businessIdSetList.add(businessIdSet);
                }
            }
            if (TableSimpleName.TABLE_BCA_INFORMATION.equals(target))
            {
                this.bcaInformationWriteCheckResult = bcaInformationProcessor.process();
                if (judgeAdd(bcaInformationWriteCheckResult))
                {
                    checkToOpenVO.setBcaInformationCheckResult(bcaInformationWriteCheckResult);
                    Set<String> businessIdSet = new HashSet<>();
                    bcaInformationWriteCheckResult.getRedundantList()
                                                  .stream()
                                                  .distinct()
                                                  .forEach(bcaInformationWrite -> businessIdSet.add(bcaInformationWrite.getBusinessDistrictId()));
                    businessIdSetList.add(businessIdSet);
                }
            }
            if (TableSimpleName.TABLE_BCA_SITUATION.equals(target))
            {
                this.bcaSituationWriteCheckResult = bcaSituationProcessor.process();
                if (judgeAdd(bcaSituationWriteCheckResult))
                {
                    checkToOpenVO.setBcaSituationCheckResult(bcaSituationWriteCheckResult);
                    Set<String> businessIdSet = new HashSet<>();
                    bcaSituationWriteCheckResult.getRedundantList()
                                                .stream()
                                                .distinct()
                                                .forEach(bcaSituationWrite -> businessIdSet.add(bcaSituationWrite.getBusinessDistrictId()));
                    businessIdSetList.add(businessIdSet);
                }
            }
            if (TableSimpleName.TABLE_COMMENT_DATA.equals(target))
            {
                this.commentDataWriteCheckResult = commentDataProcessor.process();
                if (judgeAdd(commentDataWriteCheckResult))
                {
                    checkToOpenVO.setCommentDataCheckResult(commentDataWriteCheckResult);
                    Set<String> businessIdSet = new HashSet<>();
                    commentDataWriteCheckResult.getRedundantList()
                                               .stream()
                                               .distinct()
                                               .forEach(commentDataWrite -> businessIdSet.add(commentDataWrite.getBusinessDistrictId()));
                    businessIdSetList.add(businessIdSet);
                }
            }
            if (TableSimpleName.TABLE_COMMENT_SHARE.equals(target))
            {
                this.commentShareWriteCheckResult = commentShareProcessor.process();
                if (judgeAdd(commentShareWriteCheckResult))
                {
                    checkToOpenVO.setCommentShareCheckResult(commentShareWriteCheckResult);
                    Set<String> businessIdSet = new HashSet<>();
                    commentShareWriteCheckResult.getRedundantList()
                                                .stream()
                                                .distinct()
                                                .forEach(commentShareWrite -> businessIdSet.add(commentShareWrite.getBusinessDistrictId()));
                    businessIdSetList.add(businessIdSet);
                }
            }
            if (TableSimpleName.TABLE_GENDER.equals(target))
            {
                this.genderWriteCheckResult = genderProcessor.process();
                if (judgeAdd(genderWriteCheckResult))
                {
                    checkToOpenVO.setGenderCheckResult(genderWriteCheckResult);
                    Set<String> businessIdSet = new HashSet<>();
                    genderWriteCheckResult.getRedundantList()
                                          .stream()
                                          .distinct()
                                          .forEach(genderWrite -> businessIdSet.add(genderWrite.getBusinessDistrictId()));
                    businessIdSetList.add(businessIdSet);
                }
            }
            if (TableSimpleName.TABLE_RENTAL.equals(target))
            {
                this.rentalWriteCheckResult = rentalProcessor.process();
                if (judgeAdd(rentalWriteCheckResult))
                {
                    checkToOpenVO.setRentalCheckResult(rentalWriteCheckResult);
                    Set<String> businessIdSet = new HashSet<>();
                    rentalWriteCheckResult.getRedundantList()
                                          .stream()
                                          .distinct()
                                          .forEach(rentalWrite -> businessIdSet.add(rentalWrite.getBusinessDistrictId()));
                    businessIdSetList.add(businessIdSet);
                }
            }
            if (TableSimpleName.TABLE_COMMENT.equals(target))
            {
                this.commentWriteCheckResult = commentProcessor.process();
                if (judgeCommentAdd(commentWriteCheckResult))
                {
                    checkToOpenVO.setCommentCheckResult(commentWriteCheckResult);
                }
            }
            if (TableSimpleName.TABLE_SHOPS.equals(target))
            {
                this.shopsWriteCheckResult = shopsProcessor.process();
                if (judgeAdd(shopsWriteCheckResult))
                {
                    checkToOpenVO.setShopsCheckResult(shopsWriteCheckResult);
                    Set<String> businessIdSet = new HashSet<>();
                    shopsWriteCheckResult.getRedundantList()
                                         .stream()
                                         .distinct()
                                         .forEach(shopsWrite -> businessIdSet.add(shopsWrite.getBusinessDistrictId()));
                    businessIdSetList.add(businessIdSet);
                }
            }*/
        }

        checkToOpenVO.setBusinessDistrictIdSetList(businessIdSetList);
        return checkToOpenVO;
    }

    /**
     * 校验除comment表的其他表的校验
     *
     * @param checkResult
     * @return
     */
    private<T> boolean judgeAdd(CheckResult<T> checkResult)
    {
        boolean add = false;
        /*
        分情况:
            1.除comment外的校验redundantList
                1.1校验除importDataList和messageList之外的list,均为空,且redundantList不为空且size>0
         */
        List<BusinessDistrict> lackList = checkResult.getLackList();
        List<T> emptyList = checkResult.getEmptyList();
        List<T> repeatList = checkResult.getRepeatList();
        List<T> redundantList = checkResult.getRedundantList();

        boolean lackBusiness = lackList == null || lackList.size() == 0;
        boolean emptyData = emptyList == null || emptyList.size() == 0;
        boolean repeatData = repeatList == null || repeatList.size() == 0;
        boolean redundantData = redundantList != null && redundantList.size() > 0;

        if (lackBusiness && emptyData && repeatData && redundantData)
        {
            add = true;
        }
        return add;
    }

    private boolean judgeCommentAdd(CheckResult<CommentWrite> checkResult)
    {
        boolean add = false;
        List<BusinessDistrict> lackList = checkResult.getLackList();
        List<CommentWrite> emptyList = checkResult.getEmptyList();
        List<CommentWrite> repeatList = checkResult.getRepeatList();
        List<CommentWrite> redundantList = checkResult.getRedundantList();
        List<ShopsWrite> shopsIdNotFoundList = checkResult.getShopsIdNotFoundList();
        List<CommentWrite> desIdNotFoundList = checkResult.getDesIdNotFoundList();

        boolean lackBusiness = lackList == null || lackList.size() == 0;
        boolean emptyData = emptyList == null || emptyList.size() == 0;
        boolean repeatData = repeatList == null || repeatList.size() == 0;
//        boolean redundantData = redundantList === null || redundantList.size() == 0;
//        boolean shopsIdNotFoundData = shopsIdNotFoundList != null && shopsIdNotFoundList.size() > 0;
//        boolean desIdNotFoundData = desIdNotFoundList != null && desIdNotFoundList.size() > 0;
        boolean redundantData = redundantList != null && redundantList.size() > 0;
        boolean shopsIdNotFoundData = shopsIdNotFoundList == null || shopsIdNotFoundList.size() == 0;
        boolean desIdNotFoundData = desIdNotFoundList == null || desIdNotFoundList.size() == 0;
        if (lackBusiness && emptyData && repeatData && redundantData && shopsIdNotFoundData && desIdNotFoundData)
        {
            add = true;
        }
        return add;
    }
}
