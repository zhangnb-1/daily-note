package com.ningboz.mygithubproject.designPattern.behavioral.adapter;

import com.ningboz.mygithubproject.designPattern.behavioral.DemoService;

/**
 * @ClassName: DemoAdapter
 * @author: Znb
 * @date: 2026/3/25
 * @description:
 */
public class DemoServiceServiceAdapter extends DemoService implements DemoServiceTarget {
    @Override
    public void targetMethod() {
        originMethod();
        System.out.println("转接接口：targetMethod");
    }
}
