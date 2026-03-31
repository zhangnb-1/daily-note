package com.ningboz.springaiproject.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: TimeTool
 * @author: Znb
 * @date: 2026/3/31
 * @description:
 */
@Component
public class TimeTool {
    @Tool(description = "获取当前日期")
    public String getCurrentTime(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
