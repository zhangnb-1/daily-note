package com.ningboz.mylearnproject.buddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// 动态增加类的方法
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> dynamicA = new ByteBuddy()
                .subclass(Object.class) // 继承Object
                .name("IndexArrayList") // 类名为A
                .defineMethod("dynamicMethod", String.class, Modifier.PUBLIC) // 定义新方法
                .intercept(FixedValue.value("Hello, Dynamic Method!")) // 方法返回固定值
                .make()
                .load(Main.class.getClassLoader())
                .getLoaded();

        // 创建对象并调用动态方法
        Object obj = dynamicA.getDeclaredConstructor().newInstance();
        Method method = dynamicA.getMethod("dynamicMethod");
        System.out.println(method.invoke(obj)); // 输出: Hello, Dynamic Method!
    }
}
