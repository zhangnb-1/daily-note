package com.ningboz.mylearnproject.zhoulei.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Feiyong {
    private User user;      // 客户
    private Money money;    // 费用类型
    private Double value;   // 金额
    private Tex tex;        // 对应税
    private TexType texType;    // 税大类
}
