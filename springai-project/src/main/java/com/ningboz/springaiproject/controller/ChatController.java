package com.ningboz.springaiproject.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @ClassName: ChatController
 * @author: Znb
 * @date: 2026/3/30
 * @description:
 */
@Controller
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatClient.Builder chatClientBuilder;
//    private ChatClient chatClient;

    @ResponseBody
    @GetMapping("/qwen")
    public String chatWithQWen(String message){
        ChatClient.ChatClientRequestSpec prompt = chatClientBuilder.build().prompt();
        String content = prompt.user(message).call().content();
        return content;
    }


    private final PromptTemplate poetryTemplate = new PromptTemplate(
            """
            你是一位中国古代诗人，请按一下规范用中文写诗：
            - 七言绝句。
            - 要有人物和风景的结合。 
            - 不要做过多回答，直接写诗即可。
            
            用户其他需求：{message}    
            """
    );

    @ResponseBody
    @GetMapping("/writePoetry")
    public String writePoetry(String message){
        String system = poetryTemplate.render(Map.of("message", message));
        ChatClient.ChatClientRequestSpec prompt = chatClientBuilder.build().prompt()
                .system(system)
                .user(message);
        String content = prompt.call().content();
        return content;
    }
}
