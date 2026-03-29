package com.ningboz.mygithubproject.designPattern.structer.proxy;

import com.ningboz.mygithubproject.designPattern.structer.DemoInterface;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        staticProxy();
    }

    // 动态代理
    public static void staticProxy(){
        new StaticProxy(new OriginDemoService()).demoMethod();
    }

    // 动态代理
    public static void dongtaiProxy(){
        OriginDemoService target = new OriginDemoService();

        DongtaiProxyService dongtaiProxyService = new DongtaiProxyService(target);
        DemoInterface demoInterface = (DemoInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                dongtaiProxyService
        );

        demoInterface.demoMethod();
        demoInterface.demoMethod2();
    }
}
