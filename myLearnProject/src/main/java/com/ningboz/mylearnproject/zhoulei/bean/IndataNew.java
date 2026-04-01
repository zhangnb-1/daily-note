package com.ningboz.mylearnproject.zhoulei.bean;

import lombok.Data;

import java.util.List;

@Data
public class IndataNew {
    /**
     * 收款日期
     * 客户名称
     * 输入总金额
     * 计算总金额
     * 单类费用List
     *      费用类型
     *      借
     *      贷
     *      起始时间
     *      终止时间
     */
    private String skDate;
    private String userName;
    private Double num;
    private Double calNum;
    private List<SingleRecord> recordList;
}
