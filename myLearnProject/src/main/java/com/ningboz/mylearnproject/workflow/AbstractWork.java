package com.ningboz.mylearnproject.workflow;

import lombok.Getter;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 工作流 单个work的抽象父类

/**
 * 主体需求以外 应处理的 问题：
 * 1. 日志记录
 * 2. 持久化存储
 * 3. 异常处理
 * 4. 接口扩展
 */



/**
 * 流程:  当一个work的被开启：
 *  1.  根据preWordIds 获取前置任务list
 *  2.  获取所有前置任务的result 构造当前任务的入参
 *  3.  执行任务、按指定方式保存result
 *  4.  循环当前任务的后续任务，以后续任务的id作为 lock，执行后续任务的前置任务未完成数 -1 ，当 == 0时 开启此后续任务
 *  5.
 *
 */
@Getter
public abstract class AbstractWork<P,R> {
    private P param;
    private R result;
    private String id;
    private String name;
    // 前置 workid
    private List<String> preWorkIds;
    // 后续 workid
    private List<String> postWorkIds;

    abstract public AbstractWork<P,R> getWorkById(String workId);
    abstract public List<AbstractWork<P,R>> getWorkByIdBatch(List<String> workIds);

    // 获取所有前置任务的result 构造当前任务的入参
    public P createParamByPreResult(){
        List<AbstractWork<P,R>> preWorks = getWorkByIdBatch(preWorkIds);
        List<R> collect = preWorks.stream().map(AbstractWork::getResult).collect(Collectors.toList());
        return createParamByPreResult(collect);

    }

    protected abstract P createParamByPreResult(List<R> preResultList);

    // 需要执行的任务
    abstract public R work(P param);

    public void start(){
        P param = createParamByPreResult();
        R result = work(param);
        for (String postWorkId : postWorkIds) {

        }

    }


}
