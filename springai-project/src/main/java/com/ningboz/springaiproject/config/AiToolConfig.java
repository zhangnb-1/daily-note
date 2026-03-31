package com.ningboz.springaiproject.config;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * @ClassName: AiToolConfig
 * @author: Znb
 * @date: 2026/3/31
 * @description:
 */

@Configuration
public class AiToolConfig {
    @Bean
    public ToolCallbackProvider toolCallbackProvider(ApplicationContext context) {
        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(Tool.class);
//        return MethodToolCallbackProvider.builder()
        return null;
//                .toolObjects(toolBeans)
//                .build();
    }
}


