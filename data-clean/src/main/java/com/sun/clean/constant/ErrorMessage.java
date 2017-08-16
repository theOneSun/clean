package com.sun.clean.constant;

/**
 * @authur sunjian.
 */
public enum ErrorMessage
{
    /**
     * resultMap中使用的
     */
    MISSING_BUSINESS_DISTRICT("缺失的商圈"),
    REDUNDANT_BUSINESS_DISTRICT_ID("多余的商圈ID"),

    AGE_EMPTY_DATA("dc_meal_age表的含空数据"),
    AGE_REPEAT_DATA("dc_meal_age表的重复的数据"),
    AVG_RATIO_EMPTY_DATA("dc_meal_avg_price_greater_than_eighty_comment_ratio表的含空数据"),
    AVG_RATIO_REPEAT_DATA("dc_meal_avg_price_greater_than_eighty_comment_ratio表的重复的数据"),
    AVG_SHOP_NUM_EMPTY_DATA("dc_meal_avg_price_greater_than_eighty_shop_num表的含空数据"),
    AVG_SHOP_NUM_REPEAT_DATA("dc_meal_avg_price_greater_than_eighty_shop_num表的重复的数据"),
    BCA_INDEX_EMPTY_DATA("dc_meal_bca_overview_shunt_index表的含空数据"),
    BCA_INDEX_REPEAT_DATA("dc_meal_bca_overview_shunt_index表的重复的数据"),
    BCA_INFORMATION_EMPTY_DATA("dc_meal_bca_restaurant_different_fold_shop_information表的含空数据"),
    BCA_INFORMATION_REPEAT_DATA("dc_meal_bca_restaurant_different_fold_shop_information表的重复的数据"),
    BCA_SITUATION_EMPTY_DATA("dc_meal_bca_restaurant_format_situation表的含空数据"),
    BCA_SITUATION_REPEAT_DATA("dc_meal_bca_restaurant_format_situation表的重复的数据"),
    COMMENT_EMPTY_DATA("dc_meal_comment表的含空数据"),
    COMMENT_REPEAT_DATA("dc_meal_comment表的重复的数据"),
    COMMENT_DATA_EMPTY_DATA("dc_meal_comment_data表的含空数据"),
    COMMENT_DATA_REPEAT_DATA("dc_meal_comment_data表的重复的数据"),
    COMMENT_SHARE_EMPTY_DATA("dc_meal_comment_share表的含空数据"),
    COMMENT_SHARE_REPEAT_DATA("dc_meal_comment_share表的重复的数据"),
    GENDER_EMPTY_DATA("dc_meal_gender表的含空数据"),
    GENDER_REPEAT_DATA("dc_meal_gender表的重复的数据"),
    RENTAL_EMPTY_DATA("dc_meal_rental表的含空数据"),
    RENTAL_REPEAT_DATA("dc_meal_rental表的重复的数据"),
    SHOPS_EMPTY_DATA("dc_meal_shops表的含空数据"),
    SHOPS_REPEAT_DATA("dc_meal_shops表的重复的数据"),

    /**
     * checkResult中使用的
     */
    NO_PROBLEM("数据没问题"),
    BUSINESS_DISTRICT_REDUNDANT("商圈有多余"),
    BUSINESS_DISTRICT_LACK("缺失商圈"),
    DATA_HAS_EMPTY("数据有空的"),
    DATA_HAS_REPEAT("数据有重复的"),

    /**
     * 校验comment中使用的
     */
    COMMENT_CHECK_SHOPS_ID_NO_FOUND("shopsId在src中没匹配的List<ShopsWrite>"),
    COMMENT_CHECK_DES_ID_NO_FOUND("comment中desId在shops中没有匹配的List<CommentWrite>"),
    COMMENT_CHECK_SHOPS_NO_DATA("shops无导入数据"),

    NOT_IMPORT_DATA("开放商圈后也不能导入的数据"),
    ;
    private String message;

    ErrorMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
