package com.keasar.mail.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @JmsListener(destination = "notification-queue")
    public void processNotification(String message) {
        // Broadcast to WebSocket topic
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}
