package com.ningboz.mylearnproject.zhoulei.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Indata {
    // 凭证号
    private String id;
    // 收款日期
    private Date date;
    // 客户名称
    private String userName;
    // 输入总金额
    private Double allMoney;

    // detailList
    private List<DetailData> detailList;
}
