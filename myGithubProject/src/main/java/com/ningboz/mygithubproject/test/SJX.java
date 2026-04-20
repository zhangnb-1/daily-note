package com.ningboz.mygithubproject.test;

public class SJX {
    private Point point1;
    private Point point2;
    private Point point3;

    public SJX(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public Point getPoint3() {
        return point3;
    }

    public void setPoint3(Point point3) {
        this.point3 = point3;
    }

    public double getSJXAroundLength(){
        return getLineLength(this.getPoint1(),this.getPoint2())
                +getLineLength(this.getPoint2(),this.getPoint3())
                +getLineLength(this.getPoint1(),this.getPoint3());

    }

    public double getLineLength(Point point1,Point point2){
        return Math.pow(Math.pow(point2.getX()-point1.getX(),2)+Math.pow(point2.getY()-point1.getY(),2),0.5);
    }
}
