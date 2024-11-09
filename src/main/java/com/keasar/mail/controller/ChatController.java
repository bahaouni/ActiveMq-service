package com.keasar.mail.controller;

import com.keasar.mail.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/send") // Listen for messages sent to /app/send
    @SendTo("/topic/messages") // Broadcast to all subscribers of /topic/messages
    public ChatMessage send(ChatMessage message) throws Exception {
        return message; // Return the message to be sent to the subscribers
    }
}
