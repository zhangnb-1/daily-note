package com.ningboz.springaiproject.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName: WeatherTool
 * @author: Znb
 * @date: 2026/3/31
 * @description:
 */
@Component
public class WeatherTool {
    @Tool(description = "查询指定城市在特定日期的天气状况。如果日期为空，默认查询今天。")
    public String getWeather(
            // 必须加 @ToolParam 并写清楚描述
            @ToolParam(description = "城市名称，例如：北京、上海、New York") String position,
            @ToolParam(description = "查询日期，格式为 yyyy-MM-dd，例如 2026-03-31") String date
    ) {
        // 建议在这里手动处理字符串转换，或者调用外部API
        // 比如：LocalDate.parse(date);
        return "晴天，25度";
    }
}
