package com.ningboz.mygithubproject.designPattern.structer.proxy;

import com.ningboz.mygithubproject.designPattern.structer.DemoInterface;

public class StaticProxy implements DemoInterface {
    private OriginDemoService originDemoService;

    public StaticProxy(OriginDemoService originDemoService) {
        this.originDemoService = originDemoService;
    }

    @Override
    public void demoMethod() {
        System.out.println("StaticProxy:demoMethod-before");
        originDemoService.demoMethod();
        System.out.println("StaticProxy:demoMethod-after");
    }

    @Override
    public void demoMethod2() {
        System.out.println("StaticProxy:demoMethod-before");
        originDemoService.demoMethod2();
        System.out.println("StaticProxy:demoMethod-after");
    }
}
