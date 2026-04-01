package com.ningboz.mylearnproject.interview.日常杂技;

import java.util.*;

public class ConsistentHash {
    private final SortedMap<Integer, String> circle = new TreeMap<>();
    private final int replicas;

    public ConsistentHash(int replicas, Collection<String> nodes) {
        this.replicas = replicas;
        for (String node : nodes) {
            addNode(node);
        }
    }

    public void addNode(String node) {
        for (int i = 0; i < replicas; i++) {
            int hash = hash(node + ":" + i);
            circle.put(hash, node);
        }
    }

    public void removeNode(String node) {
        for (int i = 0; i < replicas; i++) {
            circle.remove(hash(node + ":" + i));
        }
    }

    public String getNode(String key) {
        if (circle.isEmpty()) return null;
        int hash = hash(key);
        // 顺时针找第一个 >= hash 的节点
//        Map.Entry<Integer, String> entry = circle.ceilingEntry(hash);
//        if (entry == null) entry = circle.firstEntry(); // 绕回起点
//        return entry.getValue();
        return null;
    }

    private int hash(String key) {
        return key.hashCode() & 0x7FFFFFFF; // 转为正数
    }

    // 演示
    public static void main(String[] args) {
        List<String> servers = Arrays.asList("Server-A", "Server-B", "Server-C");
        ConsistentHash ch = new ConsistentHash(100, servers);

        // 测试分配
        for (int i = 0; i < 10; i++) {
            String key = "user:" + i;
            System.out.println(key + " -> " + ch.getNode(key));
        }

        // 模拟加节点
        System.out.println("\n--- Add Server-D ---");
        ch.addNode("Server-D");
        for (int i = 0; i < 10; i++) {
            String key = "user:" + i;
            System.out.println(key + " -> " + ch.getNode(key));
        }
    }
}