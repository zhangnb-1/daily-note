package com.ningboz.mygithubproject.designPattern.structer.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DongtaiProxyService implements InvocationHandler {
    private Object target;

    public DongtaiProxyService(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("DongtaiProxyService:before");
        Object invoke = method.invoke(target, args);
        System.out.println("DongtaiProxyService:after");
        return invoke;
    }
}
