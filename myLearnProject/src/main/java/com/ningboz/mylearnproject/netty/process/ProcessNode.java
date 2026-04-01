package com.ningboz.mylearnproject.netty.process;

import java.lang.reflect.Method;
import java.util.List;

// 流程节点
public class ProcessNode {
    private Object requestParam;
    private Object responseParam;
    private int nodeId;
    private List<ProcessNode> nextNodes;
    private String nodeName;
    private Method method;

    public ProcessNode() {
    }

    public ProcessNode(int nodeId, List<ProcessNode> nextNodes, String nodeName, Method method) {
        this.nodeId = nodeId;
        this.nextNodes = nextNodes;
        this.nodeName = nodeName;
        this.method = method;
    }

    public Object getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(Object requestParam) {
        this.requestParam = requestParam;
    }

    public Object getResponseParam() {
        return responseParam;
    }

    public void setResponseParam(Object responseParam) {
        this.responseParam = responseParam;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public List<ProcessNode> getNextNodes() {
        return nextNodes;
    }

    public void setNextNodes(List<ProcessNode> nextNodes) {
        this.nextNodes = nextNodes;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
