package com.ningboz.mylearnproject.buddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import java.lang.reflect.Modifier;

public class DynamicsBase {
    public DynamicsBase() {

        Class<?> dynamicA = new ByteBuddy()
                .subclass(Object.class) // 继承Object
                .name("IndexArrayList") // 类名为A
                .defineMethod("dynamicMethod", String.class, Modifier.PUBLIC) // 定义新方法
                .intercept(FixedValue.value("Hello, Dynamic Method!")) // 方法返回固定值
                .make()
                .load(Main.class.getClassLoader())
                .getLoaded();
    }
}
