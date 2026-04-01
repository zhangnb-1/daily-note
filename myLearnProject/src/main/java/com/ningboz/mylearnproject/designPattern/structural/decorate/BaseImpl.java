package com.ningboz.mylearnproject.designPattern.structural.decorate;

public class BaseImpl implements DecorateInterface{
    @Override
    public void print() {
        System.out.println("print:基础实现类！");
    }

    @Override
    public String getVal() {
        return "getVal:基础实现类！";
    }
}
