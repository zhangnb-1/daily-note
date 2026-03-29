package com.ningboz.mygithubproject.designPattern.structer.bridge.impl;

import com.ningboz.mygithubproject.designPattern.structer.bridge.Dimension1Service;

public class Dimension1ServiceImpl2 implements Dimension1Service {
    @Override
    public void dimensionMethod() {
        System.out.println("维度1: 实现2");
    }
}
