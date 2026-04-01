package com.ningboz.mylearnproject.interview.多线程.test;

public enum TaskStatus {
    INIT("初始阶段"),
    EXCUTE("执行中"),
    COMPLETE("完成"),

    ;
    private String name;

    TaskStatus(String name) {
        this.name = name;
    }
}
