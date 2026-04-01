package com.ningboz.mylearnproject.designPattern.structural.proxy;

public class CommonBaseServiceImpl implements BaseService,BaseService2{
    @Override
    public void print(String msg) {
        System.out.println("print:"+msg);
    }

    @Override
    public void print2(String msg) {
        System.out.println("print2:"+msg);
    }

    @Override
    public void print3(String msg) {
        System.out.println("print3:"+msg);
    }
}
