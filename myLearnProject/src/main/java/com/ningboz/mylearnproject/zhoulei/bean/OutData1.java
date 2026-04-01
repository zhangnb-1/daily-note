package com.ningboz.mylearnproject.zhoulei.bean;

import lombok.Data;

import java.util.List;

@Data
public class OutData1 {
    private String hesuanZB;        // 核算账簿
    private String pingzhengType;   // 凭证类别编码
    private String pingzhenghao;    // 凭证号
    private String email;           // 制单人编码
    private String date;            // 制单日期
    private String zhaiyao;         // 摘要
    private String code;            // 科目编码
    private String bizhong;         // 币种
    private String ybJieJinE;   // 原币借金额 小数点后两位
    private String bbJieJinE;   // 本币借金额 小数点后两位
    private String ybDaiJinE;   // 原币贷金额 小数点后两位
    private String bbDaiJinE;   // 本币贷金额 小数点后两位
    private List<String> fuzhuHesuanList; // 辅助核算List

    public OutData1(String pingzhenghao, String date, String zhaiyao, String code, String ybJieJinE, String bbJieJinE, String ybDaiJinE, String bbDaiJinE, List<String> fuzhuHesuanList) {
        this.hesuanZB = "G069174-001";
        this.pingzhengType = "21";
        this.email = "1601340701@qq.com";
        this.bizhong = "人民币";
        this.pingzhenghao = pingzhenghao;
        this.date = date;
        this.zhaiyao = zhaiyao;
        this.code = code;
        this.ybJieJinE = ybJieJinE;
        this.bbJieJinE = bbJieJinE;
        this.ybDaiJinE = ybDaiJinE;
        this.bbDaiJinE = bbDaiJinE;
        this.fuzhuHesuanList = fuzhuHesuanList;
    }

    public OutData1() {
        this.hesuanZB = "G069174-001";
        this.pingzhengType = "21";
        this.email = "1601340701@qq.com";
        this.bizhong = "人民币";
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
