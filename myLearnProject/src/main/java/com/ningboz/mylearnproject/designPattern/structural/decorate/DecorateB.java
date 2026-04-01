package com.ningboz.mylearnproject.designPattern.structural.decorate;

public class DecorateB implements DecorateInterface{
    DecorateInterface base;

    public DecorateB(DecorateInterface base) {
        this.base = base;
    }

    @Override
    public void print() {
        System.out.println("print:装饰加强B");
        base.print();
    }

    @Override
    public String getVal() {
        String val = base.getVal();
        val = "getVal:装饰加强B\n"+val;
        return val;
    }
}
