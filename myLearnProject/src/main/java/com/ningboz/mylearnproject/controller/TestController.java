package com.ningboz.mylearnproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: TestController
 * @author: Znb
 * @date: 2025/8/6
 * @description:
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/method1")
    @ResponseBody
    public Map<String, Object> method1(){
        return new HashMap<String, Object>(){{
            put("data",new ArrayList<Object>(){{
                add(new HashMap<String,String>(){{
                    put("id","10001");
                    put("name","zs");
                }});
            }});
            put("code","101");
        }};
    }

    @RequestMapping("/method2")
    @ResponseBody
    public String method2(String param){
        return "param: " + param;
    }
}
