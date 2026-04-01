package com.ningboz.mylearnproject.zhoulei.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OutData2 {
    private String fangxiang;        // 方向
    private String fenxiBizhong;    // 分析币种
    private String yuanbi;    // 原币
    private String zuzhiBenbi;           // 组织本币
    private String jituanBenbi;            // 集团本币
    private String quanjuBenbi;         // 全局本币
    private String neibuDanwei;            // 内部单位
    private String xjllName;         // 现金流量名称
    private String xjllCode;   // 现金流量编码

    private OutData1 outData1;

    public OutData2(String yuanbi, String zuzhiBenbi, String xjllName, String xjllCode) {
        this.fangxiang = "0";
        this.fenxiBizhong = "人民币";
        this.jituanBenbi = "0.00";
        this.quanjuBenbi = "";
        this.neibuDanwei = "";
        this.yuanbi = yuanbi;
        this.zuzhiBenbi = zuzhiBenbi;
        this.xjllName = xjllName;
        this.xjllCode = xjllCode;
    }


    //    "null_$head,
//    main_m_pk_accountingbook,
//    main_m_pk_vouchertype,
//    main_m_num,
//    main_pk_prepared,
//    main_m_prepareddate,
//    m_explanation,
//    m_accsubjcode,
//    m_pk_currtype,
//    m_debitamount,
//    m_localdebitamount,
//    m_creditamount,
//    m_localcreditamount,
//    ass_1,
//    ass_2,
//    ass_3,
//    ass_4,
//    ass_5"

//    "cashflow,
//    m_flag,
//    cashflowcurr,
//    m_money,
//    m_moneymain,
//    m_moneygroup,
//    m_moneyglobal,
//    cashflowinnercorp,
//    cashflowName,
//    cashflowCode"
}
