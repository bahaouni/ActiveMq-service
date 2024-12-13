package com.keasar.mail.controller;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    private final JmsTemplate jmsTemplate;

    public NotificationController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @MessageMapping("/notify")
    public void notify(String message) {
        // Send message to ActiveMQ
        jmsTemplate.convertAndSend("notification-queue", message);
    }
}
