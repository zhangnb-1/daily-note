package com.ningboz.mylearnproject.designPattern.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        jingtaiProxy();

        System.out.println("--------------------");

        dongtaiProxy();
    }

    private static void jingtaiProxy(){
        BaseService baseService = new ServiceImpl();
        BaseService baseServiceProxy = new ServiceProxy(baseService);

        baseServiceProxy.print("test");
        baseServiceProxy.print2("test");
    }

    private static void dongtaiProxy(){

        CommonBaseServiceImpl commonService = new CommonBaseServiceImpl();

        Object o = Proxy.newProxyInstance(commonService.getClass().getClassLoader(), commonService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("print".equals(method.getName())) {
                    System.out.println("dongtaiProxy-print：加强前");
                } else if ("print2".equals(method.getName())) {
                    System.out.println("dongtaiProxy-print2：加强前");
                } else if ("print3".equals(method.getName())) {
                    System.out.println("dongtaiProxy-print3：加强前");
                }
                Object result = method.invoke(commonService, args);
                if ("print".equals(method.getName())) {
                    System.out.println("dongtaiProxy-print：加强后");
                } else if ("print2".equals(method.getName())) {
                    System.out.println("dongtaiProxy-print2：加强后");
                } else if ("print3".equals(method.getName())) {
                    System.out.println("dongtaiProxy-print3：加强后");
                }
                return result;
            }
        });

        ((BaseService)o).print("test");
        ((BaseService)o).print2("test2");
        ((BaseService2)o).print3("test3");
//        commonBaseServiceProxy.print("test");
//        commonBaseServiceProxy.print2("test2");
//        commonBaseServiceProxy.print3("test3");
    }
}
