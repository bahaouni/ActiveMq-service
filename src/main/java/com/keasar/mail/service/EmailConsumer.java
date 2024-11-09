package com.keasar.mail.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @Autowired
    private EmailService emailService;

    @JmsListener(destination = "emailQueue")
    public void receiveEmail(String emailDetails) {
        // Parse emailDetails to get the to, subject, and body (or send the whole string for simplicity)
        String[] parts = emailDetails.split(";");
        String to = parts[0];
        String subject = parts[1];
        String body = parts[2];

        emailService.sendEmail(to, subject, body);
    }
}
