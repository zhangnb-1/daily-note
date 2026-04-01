package com.ningboz.mylearnproject.interview.data.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import sun.misc.Unsafe;

import java.util.concurrent.ExecutionException;

/**
 * @ClassName: Main
 * @author: Znb
 * @date: 2025/9/11
 * @description:
 */
@Service
public class Main {
    private static KafkaTemplate<String,String> kafkaTemplate;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String topic = "topic";
        String key = "key";
        String data = "{'id':1001,'name':'zsf'}";
        // 指定topic partition随机
        kafkaTemplate.send(topic,data);
        // 指定key 相同key放入同一个partition 保证顺序性
        kafkaTemplate.send(topic,key,data);
        // 直接指定partition
        kafkaTemplate.send(topic,2,key,data);

        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic, key, data);

        listenableFuture.addCallback((result)-> {
            System.out.println("success");
        },(throwable)->{
            System.out.println("fail");
        }) ;


        listenableFuture.get();
        Unsafe unsafe = Unsafe.getUnsafe();
//        unsafe.arrayBaseOffset()
    }

}
