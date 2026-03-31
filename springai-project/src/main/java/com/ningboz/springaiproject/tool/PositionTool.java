package com.ningboz.springaiproject.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

/**
 * @ClassName: PositionTool
 * @author: Znb
 * @date: 2026/3/31
 * @description:
 */
@Component
public class PositionTool {
    @Tool(description = "获取当前地址")
    public String getCurrentPosition(){
        return "上海";
    }
}
