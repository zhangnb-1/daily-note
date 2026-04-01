package com.ningboz.mylearnproject.designPattern.creational.factory;

public class SimpleFactory {
    public static Product createProduct(String type) throws Exception {
        switch (type){
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
            default: throw new Exception("！！！");
        }
    }
}
