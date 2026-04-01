package com.ningboz.mylearnproject.designPattern.structural.proxy;

public class ServiceImpl implements BaseService{
    @Override
    public void print(String msg) {
        System.out.println("serviceImpl:"+msg);
    }

    @Override
    public void print2(String msg) {
        System.out.println("serviceImpl-print2:"+msg);
    }
}
