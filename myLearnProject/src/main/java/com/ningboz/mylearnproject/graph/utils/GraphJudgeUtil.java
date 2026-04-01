package com.ningboz.mylearnproject.graph.utils;

import com.ningboz.mylearnproject.graph.bean.Point;
import com.ningboz.mylearnproject.graph.bean.Vector;

//  几何判断工具类
public class GraphJudgeUtil {
    // 默认角度误差值
    private static final double defAngleTolerance = 5d;
    // 默认数值误差绝对值
    private static final double defNumTolerance = 100d;
    // 默认数值误差相对值
    private static final double defNumRelaTolerance = 0.005;

    /**
     * 近似判断
     * @param val1
     * @param val2
     * @param tolerance 绝对误差值
     * @return
     */
    private boolean similar(double val1,double val2,double tolerance){
        return Math.abs(val1-val2) < tolerance;
    }

    /**
     * 近似判断
     * @param val1
     * @param val2
     * @param relaTolerance 相对误差值 比如0.005
     * @return
     */
    private boolean relaSimilar(double val1,double val2,double relaTolerance){
        return val1*val2>0 && 1-Math.min(val1,val2)/Math.max(val1,val2)<relaTolerance;
    }

    /**
     * 严格近似判断   相对绝对都要近似才符合
     * @param val1
     * @param val2
     * @param relaTolerance 相对误差值 比如0.005
     * @return
     */
    private boolean similarStrict(double val1,double val2,double tolerance,double relaTolerance){
        return similar(val1,val2,tolerance) && relaSimilar(val1,val2,relaTolerance);
    }

    /**
     * 宽松近似判断   相对绝对有一个近似即符合
     * @param val1
     * @param val2
     * @param relaTolerance 相对误差值 比如0.005
     * @return
     */
    private boolean similarLoose(double val1,double val2,double tolerance,double relaTolerance){
        return similar(val1,val2,tolerance) || relaSimilar(val1,val2,relaTolerance);
    }

    /**
     * 要做的几何判断的总集
     * 1.点
     *  1.1 点自身
     *      身处哪个象限
     *  1.2 点、线关系
     * 2.线
     * 3.面
     */

    // 判断平行
    private boolean isPingxing(Vector vector1,Vector vector2,double angleTolerance){
        double angle = GraphComputeUtil.angle(vector1, vector2);
        return similar(angle,0,angleTolerance) || similar(angle,180,angleTolerance);
    }

    // 判断平行
    private boolean isPingxing(Vector vector1,Vector vector2){
        double angle = GraphComputeUtil.angle(vector1, vector2);
        return similar(angle,0,defAngleTolerance) || similar(angle,180,defAngleTolerance);
    }

    // 判断垂直
    private boolean isChuizhi(Vector vector1,Vector vector2,double angleTolerance){
        double angle = GraphComputeUtil.angle(vector1, vector2);
        return similar(angle,0,angleTolerance) || similar(angle,180,angleTolerance);
    }

    // 判断垂直
    private boolean isChuizhi(Vector vector1,Vector vector2){
        double angle = GraphComputeUtil.angle(vector1, vector2);
        return similar(angle,90,defAngleTolerance) || similar(angle,270,defAngleTolerance);
    }

    // 判断共线
    private boolean isGongxian(Point point1, Point point2, Point point3){
        return false;
    }
}
