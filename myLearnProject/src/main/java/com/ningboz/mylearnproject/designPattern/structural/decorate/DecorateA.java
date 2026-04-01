package com.ningboz.mylearnproject.designPattern.structural.decorate;

public class DecorateA implements DecorateInterface{
    DecorateInterface base;

    public DecorateA(DecorateInterface base) {
        this.base = base;
    }

    @Override
    public void print() {
        System.out.println("print:装饰加强A");
        base.print();
    }

    @Override
    public String getVal() {
        String val = base.getVal();
        val = "getVal:装饰加强A\n"+val;
        return val;
    }
}
