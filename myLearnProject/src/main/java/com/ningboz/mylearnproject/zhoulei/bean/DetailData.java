package com.ningboz.mylearnproject.zhoulei.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailData {
    private String  type;
    private String timeDesc; // 款项期间描述
    private String brandName;   // 品牌名称 仅物业费
    private Double  money;
    private Boolean isJie;
    private Indata indata;
}
