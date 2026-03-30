package com.ningboz.mygithubproject.jmm.volatileJMM.entity;


import org.springframework.stereotype.Component;

/**
 * @ClassName: SingleBean
 * @author: Znb
 * @date: 2026/3/27
 * @description:
 */
@Component
public class SingleBean {
    private static volatile SingleBean singleBean;
    private static SingleBean singleBean2;

    private SingleBean(){}

    public SingleBean getInstance(){
        if(singleBean == null){
            synchronized (this){
                if(singleBean == null)
                    singleBean = new SingleBean();
            }
        }
        return singleBean;
    }

    public SingleBean getInstance2(){
        if(singleBean2 == null){
            synchronized (this){
                if(singleBean2 == null)
                    singleBean2 = new SingleBean();
            }
        }
        return singleBean2;
    }
}
