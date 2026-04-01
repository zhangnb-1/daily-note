package com.ningboz.mylearnproject.buddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import java.lang.reflect.Modifier;

@DynamicMethod(methodName = "dynamicHello", returnType = String.class)
public class A {
    public static void main(String[] args) throws Exception {
        // 动态生成类并实现存根接口
//        Class<?> dynamicA = new ByteBuddy()
//                .subclass(A.class)
//                .implement(AStub.class) // 实现存根接口
//                .defineMethod("dynamicHello", String.class, Modifier.PUBLIC)
//                .intercept(FixedValue.value("Hello from Dynamic Method!"))
//                .make()
//                .load(A.class.getClassLoader())
//                .getLoaded();
//
//        // 创建对象并调用方法（IDE 可识别）
//        Object obj = dynamicA.getDeclaredConstructor().newInstance();
//        System.out.println(((AStub) obj).dynamicHello());
    }
}
