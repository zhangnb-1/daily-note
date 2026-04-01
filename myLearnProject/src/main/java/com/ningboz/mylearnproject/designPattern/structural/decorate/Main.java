package com.ningboz.mylearnproject.designPattern.structural.decorate;

public class Main {
    public static void main(String[] args) {
        DecorateInterface base = new BaseImpl();
        base = new DecorateB(base);
        base = new DecorateA(base);

        base.print();

        System.out.println(base.getVal());
    }
}
