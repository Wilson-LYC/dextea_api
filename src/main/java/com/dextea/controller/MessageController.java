package com.dextea.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dextea.handler.WebSocketHandler;

import java.io.IOException;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private WebSocketHandler webSocketHandler;

    /**
     * 推送消息
     */
    @PostMapping("/push")
    public String pushMessage(@RequestParam("message") String message) throws IOException {
        webSocketHandler.sendMessage(message);
        return "success";
    }

}

