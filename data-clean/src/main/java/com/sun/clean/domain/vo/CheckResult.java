package com.sun.clean.domain.vo;


import com.sun.clean.constant.ErrorMessage;
import com.sun.clean.domain.BusinessDistrict;
import com.sun.clean.domain.write.CommentWrite;
import com.sun.clean.domain.write.ShopsWrite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 封装校验的结果
 * @authur sunjian.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckResult<T>
{
    /**
     * 插入的数据的集合
     */
    private List<T> importDataList;

    /**
     * 多余的商圈的数据
     */
    private List<T> redundantList;

    /**
     * 缺少的商圈的集合
     */
    private List<BusinessDistrict> lackList;

    /**
     * 数据有空的集合
     */
    private List<T> emptyList;

    /**
     * 数据有重复的集合
     */
    private List<T> repeatList;

    /**
     * desId在shopsId中未匹配的数据
     */
    private List<CommentWrite> desIdNotFoundList;

    /**
     * shopsId在comment的srcId中未匹配的集合
     */
    private List<ShopsWrite> shopsIdNotFoundList;

    /**
     * 问题描述
     */
    private List<ErrorMessage> messageList;

    public CheckResult(Builder<T> builder) {
        this.importDataList = builder.importDataList;
        this.redundantList = builder.redundantList;
        this.lackList = builder.lackList;
        this.emptyList = builder.emptyList;
        this.repeatList = builder.repeatList;
        this.desIdNotFoundList = builder.desIdNotFoundList;
        this.shopsIdNotFoundList = builder.shopsIdNotFoundList;
        this.messageList = builder.messageList;
    }

    public static class Builder<T>{
        private List<T> importDataList;
        private List<T> redundantList;
        private List<BusinessDistrict> lackList;
        private List<T> emptyList;
        private List<T> repeatList;
        private List<CommentWrite> desIdNotFoundList;
        private List<ShopsWrite> shopsIdNotFoundList;
        private List<ErrorMessage> messageList;

        public Builder()
        {
        }

        public Builder<T> importDataList(List<T> importDataList){
            this.importDataList = importDataList;
            return this;
        }
        public Builder<T> redundantList(List<T> redundantList){
            this.redundantList = redundantList;
            return this;
        }
        public Builder<T> lackList(List<BusinessDistrict> lackList){
            this.lackList = lackList;
            return this;
        }
        public Builder<T> emptyList(List<T> emptyList){
            this.emptyList = emptyList;
            return this;
        }
        public Builder<T> repeatList(List<T> repeatList){
            this.repeatList = repeatList;
            return this;
        }
        public Builder<T> desIdNotFoundList(List<CommentWrite> desIdNotFoundList){
            this.desIdNotFoundList = desIdNotFoundList;
            return this;
        }
        public Builder<T> shopsIdNotFoundList(List<ShopsWrite> shopsIdNotFoundList){
            this.shopsIdNotFoundList = shopsIdNotFoundList;
            return this;
        }
        public Builder<T> messageList(List<ErrorMessage> messageList){
            this.messageList = messageList;
            return this;
        }

        public CheckResult<T> build(){
            return new CheckResult<>(this);
        }
    }
}
