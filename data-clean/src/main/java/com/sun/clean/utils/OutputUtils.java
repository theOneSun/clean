package com.sun.clean.utils;

import com.sun.clean.constant.ErrorMessage;
import com.sun.clean.constant.InfoMessage;
import com.sun.clean.domain.BusinessDistrict;
import com.sun.clean.domain.vo.CheckResult;
import com.sun.clean.domain.vo.DispatchDataVO;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @authur sunjian.
 */
public class OutputUtils
{
    /**
     * 输出校验结果
     *
     * @param table
     * @param checkResult
     * @throws IOException
     */
    public static <T> void printCheckResultToFile(String table, CheckResult<T> checkResult) throws Exception
    {
        String userDir = System.getProperty("user.dir");
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd");
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);

        File file = new File(userDir + "/导入数据结果" + format);
        boolean exists = file.exists();
        if (!exists)
        {
            //不存在就创建一个
            boolean success = file.mkdir();
            System.out.println("创建文件夹:" + success);
            if (!success){
                throw new Exception("创建文件夹失败");
            }
        }

        FileWriter fileWriter = new FileWriter(file + "/" + table + ".txt");
        BufferedWriter bw = new BufferedWriter(fileWriter);

        //输出校验信息
        List<ErrorMessage> messageList = checkResult.getMessageList();
        if (messageList != null && messageList.size() > 0)
        {
            for (ErrorMessage message : messageList)
            {
                bufferedWrite(bw, message.getMessage());
            }
        }
        //输出导入的数据
        List<T> importDataList = checkResult.getImportDataList();
        if (importDataList != null && importDataList.size() > 0)
        {
            //bufferedWrite(bw, InfoMessage.IMPORT_DATA.getMessage() + "=" + importDataList.toString());
            bufferedWrite(bw, InfoMessage.IMPORT_DATA.getMessage(), importDataList);
        }
        //输出多余的商圈的数据
        List<T> redundantList = checkResult.getRedundantList();
        if (redundantList != null && redundantList.size() > 0)
        {
            //bufferedWrite(bw, InfoMessage.REDUNDANT_BUSINESS_DISTRICT.getMessage() + "=" + redundantList.toString());
            bufferedWrite(bw, InfoMessage.REDUNDANT_BUSINESS_DISTRICT.getMessage(), redundantList);
        }
        //输出缺失的商圈的集合
        List<BusinessDistrict> lackList = checkResult.getLackList();
        if (lackList != null && lackList.size() > 0)
        {
            //bufferedWrite(bw, InfoMessage.LACK_BUSINESS_DISTRICT.getMessage() + "=" + lackList.toString());
            bufferedWrite(bw, InfoMessage.LACK_BUSINESS_DISTRICT.getMessage(), lackList);
        }
        //输出数据有缺失的数据集合
        List<T> emptyList = checkResult.getEmptyList();
        if (emptyList != null && emptyList.size() > 0)
        {
            //bufferedWrite(bw, InfoMessage.EMPTY_DATA.getMessage() + "=" + emptyList.toString());
            bufferedWrite(bw, InfoMessage.EMPTY_DATA.getMessage(), emptyList);
        }
        //输出数据有重复的数据集合
        List<T> repeatList = checkResult.getRepeatList();
        if (repeatList != null && repeatList.size() > 0)
        {
            //bufferedWrite(bw, InfoMessage.REPEAT_DATA.getMessage() + "=" + repeatList.toString());
            bufferedWrite(bw, InfoMessage.REPEAT_DATA.getMessage(), repeatList);
        }
        //输出commentCheck中的问题数据集合
        List<ShopsWrite> shopsIdNotFoundList = checkResult.getShopsIdNotFoundList();
        if (shopsIdNotFoundList != null && shopsIdNotFoundList.size() > 0)
        {
            //bufferedWrite(bw, InfoMessage.SHOPS_NOT_FOUND_IN_COMMENT.getMessage() + "=" + shopsIdNotFoundList.toString());
            bufferedWrite(bw, InfoMessage.SHOPS_NOT_FOUND_IN_COMMENT.getMessage(), shopsIdNotFoundList);
        }
        //输出commentCheck中的问题数据集合
        List<CommentWrite> desIdNotFoundList = checkResult.getDesIdNotFoundList();
        if (desIdNotFoundList != null && desIdNotFoundList.size() > 0)
        {
            //bufferedWrite(bw, InfoMessage.COMMENT_NOT_FOUND_IN_SHOPS.getMessage() + "=" + desIdNotFoundList.toString());
            bufferedWrite(bw, InfoMessage.COMMENT_NOT_FOUND_IN_SHOPS.getMessage(), desIdNotFoundList);
        }
        bw.close();
    }

    /**
     * 输出开放商圈的结果
     *
     * @param dispatchDataVO
     * @throws IOException
     */
    public static void writeOpenBusinessDistrictToFile(DispatchDataVO dispatchDataVO) throws IOException
    {
        String userDir = System.getProperty("user.dir");
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd");
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);

        File file = new File(userDir + "/开放商圈结果" + format);
        boolean exists = file.exists();
        if (!exists)
        {
            //不存在就创建一个
            file.mkdir();
        }

        writeDispatchData(dispatchDataVO, file);
    }


    /**
     * 输出剩余没有导入的数据
     *
     * @param dispatchDataVO
     * @param file
     * @throws IOException
     */
    private static void writeDispatchData(DispatchDataVO dispatchDataVO, File file) throws IOException
    {
        RemainDataVO remainDataVO = dispatchDataVO.getRemainDataVO();
        FileWriter fileWriter;
        BufferedWriter bw;

        List<AgeWrite> ageWriteList = remainDataVO.getAgeWriteList();
        fileWriter = new FileWriter(file + "/age.txt");
        bw = new BufferedWriter(fileWriter);
        bufferedWrite(bw, ErrorMessage.NOT_IMPORT_DATA.getMessage(), ageWriteList);
        //-------------------------------------------------------------------------------
        List<AvgRatioWrite> avgRatioWriteList = remainDataVO.getAvgRatioWriteList();
        fileWriter = new FileWriter(file + "/avgRatio.txt");
        bw = new BufferedWriter(fileWriter);
        bufferedWrite(bw, ErrorMessage.NOT_IMPORT_DATA.getMessage(), avgRatioWriteList);
        //--------------------------------------------------------------------------------
        List<AvgShopNumWrite> avgShopNumWriteList = remainDataVO.getAvgShopNumWriteList();
        fileWriter = new FileWriter(file + "/avgShopNum.txt");
        bw = new BufferedWriter(fileWriter);
        bufferedWrite(bw, ErrorMessage.NOT_IMPORT_DATA.getMessage(), avgShopNumWriteList);
        //--------------------------------------------------------------------------------
        List<BcaIndexWrite> bcaIndexWriteList = remainDataVO.getBcaIndexWriteList();
        fileWriter = new FileWriter(file + "/bcaIndex.txt");
        bw = new BufferedWriter(fileWriter);
        bufferedWrite(bw, ErrorMessage.NOT_IMPORT_DATA.getMessage(), bcaIndexWriteList);
        //--------------------------------------------------------------------------------
        List<BcaInformationWrite> bcaInformationWriteList = remainDataVO.getBcaInformationWriteList();
        fileWriter = new FileWriter(file + "/bcaInformation.txt");
        bw = new BufferedWriter(fileWriter);
        bufferedWrite(bw, ErrorMessage.NOT_IMPORT_DATA.getMessage(), bcaInformationWriteList);
        //--------------------------------------------------------------------------------
        List<BcaSituationWrite> bcaSituationWriteList = remainDataVO.getBcaSituationWriteList();
        fileWriter = new FileWriter(file + "/bcaSituation.txt");
        bw = new BufferedWriter(fileWriter);
        bufferedWrite(bw, ErrorMessage.NOT_IMPORT_DATA.getMessage(), bcaSituationWriteList);
        //--------------------------------------------------------------------------------
        List<CommentWrite> commentWriteList = remainDataVO.getCommentWriteList();
        fileWriter = new FileWriter(file + "/comment.txt");
        bw = new BufferedWriter(fileWriter);
        bufferedWrite(bw, ErrorMessage.NOT_IMPORT_DATA.getMessage(), commentWriteList);
        //--------------------------------------------------------------------------------
        List<CommentDataWrite> commentDataWriteList = remainDataVO.getCommentDataWriteList();
        fileWriter = new FileWriter(file + "/commentData.txt");
        bw = new BufferedWriter(fileWriter);
        bufferedWrite(bw, ErrorMessage.NOT_IMPORT_DATA.getMessage(), commentDataWriteList);
        //--------------------------------------------------------------------------------
        List<CommentShareWrite> commentShareWriteList = remainDataVO.getCommentShareWriteList();
        fileWriter = new FileWriter(file + "/commentShare.txt");
        bw = new BufferedWriter(fileWriter);
        bufferedWrite(bw, ErrorMessage.NOT_IMPORT_DATA.getMessage(), commentShareWriteList);
        //--------------------------------------------------------------------------------
        List<GenderWrite> genderWriteList = remainDataVO.getGenderWriteList();
        fileWriter = new FileWriter(file + "/gender.txt");
        bw = new BufferedWriter(fileWriter);
        bufferedWrite(bw, ErrorMessage.NOT_IMPORT_DATA.getMessage(), genderWriteList);
        //--------------------------------------------------------------------------------
        List<RentalWrite> rentalWriteList = remainDataVO.getRentalWriteList();
        fileWriter = new FileWriter(file + "/rental.txt");
        bw = new BufferedWriter(fileWriter);
        bufferedWrite(bw, ErrorMessage.NOT_IMPORT_DATA.getMessage(), rentalWriteList);
        //--------------------------------------------------------------------------------
        List<ShopsWrite> shopsWriteList = remainDataVO.getShopsWriteList();
        fileWriter = new FileWriter(file + "/shops.txt");
        bw = new BufferedWriter(fileWriter);
        bufferedWrite(bw, ErrorMessage.NOT_IMPORT_DATA.getMessage(), shopsWriteList);
    }

    private static void bufferedWrite(BufferedWriter bw, String content) throws IOException
    {
        bw.write(content);
        bw.newLine();
        bw.flush();
    }

    private static <T> void bufferedWrite(BufferedWriter bw, String key, List<T> list) throws IOException
    {
        /*
        判断长度,截取插入
        1.3000条为一组
        2.size/3000 && size%3000
        2.1如果整除,就循环这么多次
        2.2如果有余数,循环商+1次
         */
        int size = list.size();
        List writeList;
        if (size > 3000)
        {
            int result = size / 3000;//商
            int remainder = size % 3000;//余数
            if (remainder > 0)
            {
                //没有整除,循环result+1次
                result += 1;
            }
            bw.newLine();
            bw.write(key + "=");
            bw.flush();
            for (int i = 0; i < result; i++)
            {
                writeList = list.stream().skip(i * 3000).limit(3000).collect(Collectors.toList());
                bw.write(writeList.toString());
                bw.newLine();
                bw.flush();
            }
        } else
        {
            bw.newLine();
            bw.write(key + "=" + list.toString());
            bw.flush();
        }
    }
}
