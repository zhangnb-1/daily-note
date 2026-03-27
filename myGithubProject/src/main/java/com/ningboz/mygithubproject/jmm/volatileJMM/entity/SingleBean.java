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
}
