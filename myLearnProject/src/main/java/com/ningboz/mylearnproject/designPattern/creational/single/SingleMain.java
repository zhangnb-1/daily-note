package com.ningboz.mylearnproject.designPattern.creational.single;

/**
 * 单例模式
 * 单例模式 和 工具静态类的区别以及取舍：
 *      单例是实例化对象，可以通过继承，实现将需求层级化实现，静态类是单纯的静态方法，没有继承，没有层级关系
 */
public class SingleMain {
    private static volatile SingleMain instance;
    private SingleMain(){}

    public static SingleMain getInstance(){
        if(instance==null){
            synchronized (SingleMain.class){
                if(instance==null){
                    instance = new SingleMain();
                }
            }
        }
        return instance;
    }
}
