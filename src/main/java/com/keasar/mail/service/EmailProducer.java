package com.keasar.mail.service;



import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {
    private final JmsTemplate jmsTemplate;

    public EmailProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendEmailMessage(String email) {
        jmsTemplate.convertAndSend("emailQueue", email);
    }
}
