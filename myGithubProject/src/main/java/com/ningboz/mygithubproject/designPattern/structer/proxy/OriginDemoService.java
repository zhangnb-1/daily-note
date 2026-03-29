package com.ningboz.mygithubproject.designPattern.structer.proxy;

import com.ningboz.mygithubproject.designPattern.structer.DemoInterface;

public class OriginDemoService implements DemoInterface {
    @Override
    public void demoMethod() {
        System.out.println("OriginDemoService:demoMethod");
    }

    @Override
    public void demoMethod2() {
        System.out.println("OriginDemoService:demoMethod2");
    }
}
