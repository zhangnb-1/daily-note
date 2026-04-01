package com.ningboz.mylearnproject.netty.process;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ProcessController {
    private Stack<ProcessNode> processStack = new Stack<>();
    private ProcessNode root;
    private ProcessNode cur;

    public ProcessController(ProcessNode root) {
        this.root = root;
        this.cur = root;
        processStack.push(root);
    }

    public void start() throws Exception {
//        while()
    }

    private void run() throws Exception {
        // 展示页面
        // 接受命令
        // 执行命令
        Object response = execute(cur);
        showPage(cur);
        Scanner sc = new Scanner(System.in);
        int next = sc.nextInt();
        if(next<0){
            // 撤回
            if(processStack.isEmpty())
                cur = null;
            else cur = processStack.pop();
        }else if(next>cur.getNextNodes().size()-1){
            // 命令非法
            throw new Exception("非法异常命令");
        }else {
            ProcessNode nextProcess = cur.getNextNodes().get(next);
            processStack.push(cur);


            cur = nextProcess;
        }

    }

    private Object execute(ProcessNode processNode) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Method method = processNode.getMethod();
        if(method == null)
            return null;
        Object response = null;
        if(processNode.getRequestParam()==null)
            response = method.invoke(method.getDeclaringClass().newInstance());
        else response = method.invoke(method.getDeclaringClass().newInstance(),processNode.getRequestParam());
        return response;
    }

    private void showPage(ProcessNode processNode){

        List<ProcessNode> nodes = processNode.getNextNodes();

    }
}
