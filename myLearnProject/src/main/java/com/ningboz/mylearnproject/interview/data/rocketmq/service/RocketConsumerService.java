package com.ningboz.mylearnproject.interview.data.rocketmq.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ConsumerService
 * @author: Znb
 * @date: 2025/9/18
 * @description:
 */
@RocketMQMessageListener(consumerGroup = "group1",topic = "topic1")
@Component
public class RocketConsumerService implements RocketMQListener<String> {
    @Override
    public void onMessage(String msg) {
        System.out.println(msg);
    }
}
