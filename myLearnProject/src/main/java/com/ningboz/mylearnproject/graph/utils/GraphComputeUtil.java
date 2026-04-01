package com.ningboz.mylearnproject.graph.utils;

import com.ningboz.mylearnproject.graph.bean.Line;
import com.ningboz.mylearnproject.graph.bean.LineSeg;
import com.ningboz.mylearnproject.graph.bean.Point;
import com.ningboz.mylearnproject.graph.bean.Vector;

// 几何工具类，做判断和计算值
public class GraphComputeUtil {
    public static double min(double... val){
        double result = Double.MAX_VALUE;
        for (double v : val) {
            if(v<result)    result = v;
        }
        return result;
    }

    public double max(double... val){
        double result = Double.MIN_VALUE;
        for (double v : val) {
            if(v>result)    result = v;
        }
        return result;
    }

    // 点乘
    public static double vectorDot(Vector v1, Vector v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY();
    }

    // 叉乘（二维向量的叉乘结果是一个标量）
    public static double vectorCross(Vector v1, Vector v2) {
        return v1.getX() * v2.getY() - v1.getY() * v2.getX();
    }

    // 距离-点点
    public static double distance(Point point1,Point point2){
        return Math.hypot(point1.getX()- point2.getX(),point1.getY()- point2.getY());
    }

    //  距离-点线 带正负方向
    public static double directedDistance(Point p, Line line){
        Vector AP = new Vector(p.getX() - line.getPoint().getX(), p.getY() - line.getPoint().getY());
        double crossProduct = vectorCross(AP, line.getVector());
        double magnitude = line.getVector().mold();

        return crossProduct / magnitude;
    }

    //  距离-点线 不带正负方向
    public static double distance(Point p, Line line){
        return Math.abs(directedDistance(p,line));
    }

    //  距离-点到线段 带正负方向
    public static double directedDistance(Point p, LineSeg line){
        double dis0 = directedDistance(p, new Line(line.getPoint1(), line.getVector()));
        double dis1 = distance(p, line.getPoint1());
        double dis2 = distance(p, line.getPoint2());
        double res = min(Math.abs(dis0), dis1, dis2);
        if(dis0<0)  res = -res;
        return res;
    }

    //  距离-点到线段 不带正负方向
    public static double distance(Point p, LineSeg line){
        return Math.abs(directedDistance(p,line));
    }

    //  向量的角度
    public static double angle(Vector v1,Vector v2){
        double dotProd = vectorDot(v1, v2);
        double magnitudeV1 = v1.mold();
        double magnitudeV2 = v2.mold();

        if (magnitudeV1 == 0 || magnitudeV2 == 0) {
            throw new IllegalArgumentException("Vector magnitude cannot be zero");
        }

        double cosTheta = dotProd / (magnitudeV1 * magnitudeV2);
        // 确保cosTheta在-1到1之间，防止由于浮点数精度问题导致超出范围
        cosTheta = Math.max(-1.0, Math.min(1.0, cosTheta));

        return Math.acos(cosTheta);
    }

}
