package com.ningboz.mylearnproject.designPattern.structural.proxy;

public class ServiceProxy implements BaseService{
    private BaseService baseService;

    public ServiceProxy(BaseService baseService) {
        this.baseService = baseService;
    }

    @Override
    public void print(String msg) {
        System.out.println("serviceProxy 前加强");
        baseService.print(msg);
        System.out.println("serviceProxy 后加强");
    }

    @Override
    public void print2(String msg) {
        System.out.println("serviceProxy-print2 前加强");
        baseService.print2(msg);
        System.out.println("serviceProxy-print2 后加强");
    }
}
