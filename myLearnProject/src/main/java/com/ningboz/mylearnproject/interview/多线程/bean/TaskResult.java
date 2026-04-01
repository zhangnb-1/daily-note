package com.ningboz.mylearnproject.interview.多线程.bean;

import cn.hutool.extra.spring.SpringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: TaskResult
 * @author: Znb
 * @date: 2025/7/31
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
public class TaskResult {
    private Boolean isSuccess;
    private String msg;
    private Object result;
}
