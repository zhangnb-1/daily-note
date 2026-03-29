package com.ningboz.mygithubproject.designPattern.structer.bridge;

import com.ningboz.mygithubproject.designPattern.structer.bridge.impl.*;

public class Main {
    public static void main(String[] args) {
        new CommonService(new Dimension1ServiceImpl2(),new Dimension2ServiceImpl2(),new Dimension3ServiceImpl2()).doSomething();
    }
}
