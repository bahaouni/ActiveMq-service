package com.keasar.mail.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @JmsListener(destination = "notificationTopic")
    public void receiveNotification(String message) {
        System.out.println("Received notification: " + message);
        // Handle the message (e.g., send it to the frontend or log it)
    }
}
