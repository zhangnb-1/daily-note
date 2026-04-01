package com.ningboz.mylearnproject.graph.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// 直线
public class Line {
    // 直线任意一点
    private Point point;
    // 直线向量
    private Vector vector;
}
