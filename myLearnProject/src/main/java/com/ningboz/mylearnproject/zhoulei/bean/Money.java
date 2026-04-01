package com.ningboz.mylearnproject.zhoulei.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Money {
    private String id;      // 科目编码
    private String name;    // 费用类型
    private String texId;   // 税费科目编码
    private String texName; // 税率名称
    private Double texPercent;  // 税率
    private String texTypeCode; // 税大类code

    private String xjllName;    // 现金流量名称
    private String xjllCode;    // 现金流量编码
    // 是否拆分
    private Boolean splitFlag;

}
