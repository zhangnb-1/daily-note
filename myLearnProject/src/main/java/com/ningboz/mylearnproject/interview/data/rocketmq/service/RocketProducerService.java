package com.ningboz.mylearnproject.interview.data.rocketmq.service;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ProducerService
 * @author: Znb
 * @date: 2025/9/18
 * @description:
 */
@Service
public class RocketProducerService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private final String topic = "topic1";

    public <T> void sendMsg(T msg){
        rocketMQTemplate.sendAndReceive(topic, msg, msg.getClass());
    }
}
