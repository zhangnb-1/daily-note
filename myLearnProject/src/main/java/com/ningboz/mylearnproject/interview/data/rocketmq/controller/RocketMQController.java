package com.ningboz.mylearnproject.interview.data.rocketmq.controller;

import com.ningboz.mylearnproject.interview.data.rocketmq.service.RocketProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: TestController
 * @author: Znb
 * @date: 2025/9/18
 * @description:
 */
@Controller
@RequestMapping("/rocketmq")
public class RocketMQController {
    @Autowired
    private RocketProducerService producerService;

    @RequestMapping("/test1/{msg}")
    @ResponseBody
    public String test1(@PathVariable String msg){
        producerService.sendMsg(msg);
        return "一条rocketmq测试消息:"+msg;
    }
}
