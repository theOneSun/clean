package com.sun.clean.constant;

/**
 * @authur sunjian.
 */
public enum InfoMessage
{
    IMPORT_DATA("导入的数据"),
    EMPTY_DATA("含空的数据"),
    REPEAT_DATA("重复的数据"),
    LACK_BUSINESS_DISTRICT("缺失的商圈"),
    REDUNDANT_BUSINESS_DISTRICT("未开放商圈的数据"),
    SHOPS_NOT_FOUND_IN_COMMENT("shopsId在srcId未找到的shops表数据"),
    COMMENT_NOT_FOUND_IN_SHOPS("desId在shopsId未找到的comment表数据")
    ;

    private String message;

    InfoMessage(String message)
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
