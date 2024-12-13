package com.keasar.mail.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class UserRegistrationConsumer {

    private final JavaMailSender mailSender;

    @Autowired
    public UserRegistrationConsumer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // This method will consume messages from the ActiveMQ queue
    @JmsListener(destination = "userRegistrationQueue")
    public void receiveUserRegistrationMessage(String message) {
        // Extract user details from the message (here, we expect "New user registered: username, email")
        String[] userDetails = message.split(":")[1].split(",");
        String username = userDetails[0].trim();
        String email = userDetails[1].trim();

        // Send confirmation email
        sendConfirmationEmail(email, username);
    }

    public void sendConfirmationEmail(String email, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Welcome to Our Service!");
        message.setText(String.format("Hello %s, \n\nWelcome to our service! We are glad to have you with us.", username));
        mailSender.send(message);
    }
}
