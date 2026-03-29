package com.ningboz.mygithubproject.designPattern.structer.bridge.impl;

import com.ningboz.mygithubproject.designPattern.structer.bridge.Dimension3Service;

public class Dimension3ServiceImpl1 implements Dimension3Service {
    @Override
    public void dimensionMethod() {
        System.out.println("维度3: 实现1");
    }
}
