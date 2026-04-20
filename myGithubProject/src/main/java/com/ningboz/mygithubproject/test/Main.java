package com.ningboz.mygithubproject.test;

public class Main {
    public static void main(String[] args) {
        SJX sjx1 = new SJX(new Point(-1d, 0d), new Point(1d, 0d), new Point(0d, 2d));
//        System.out.println(getSJXAroundLength(sjx1));
        System.out.println(sjx1.getSJXAroundLength());
    }

    // 获取三角形周长
//    private static double getSJXAroundLength(SJX sjx){
//        return getLineLength(sjx.getPoint1(),sjx.getPoint2())
//                +getLineLength(sjx.getPoint2(),sjx.getPoint3())
//                +getLineLength(sjx.getPoint1(),sjx.getPoint3());
//
//    }
//
//    private static double getLineLength(Point point1,Point point2){
//        return Math.pow(Math.pow(point2.getX()-point1.getX(),2)+Math.pow(point2.getY()-point1.getY(),2),0.5);
//    }
}
