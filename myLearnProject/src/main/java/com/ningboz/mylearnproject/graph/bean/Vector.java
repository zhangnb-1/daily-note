package com.ningboz.mylearnproject.graph.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// 向量
public class Vector {
    private double x;
    private double y;

    public double mold(){
        return Math.hypot(x,y);
    }

    // 左转
    public Vector left(){
        return new Vector(-y,x);
    }

    // 右转
    public Vector right(){
        return new Vector(y,-x);
    }

    // 倒置
    public Vector reverse(){
        return new Vector(-x,-y);
    }

    public Vector(Point point1,Point point2) {
        this.x = point1.getX()-point2.getX();
        this.y = point1.getY()-point2.getY();
    }
}
