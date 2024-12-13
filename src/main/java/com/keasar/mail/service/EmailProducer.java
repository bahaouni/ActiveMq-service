package com.keasar.mail.service;



import com.keasar.mail.model.MailDTO;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmailProducer {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public EmailProducer(JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendEmailMessage(MailDTO email) {
        try {
            String emailJson = objectMapper.writeValueAsString(email);
            jmsTemplate.convertAndSend("emailQueue", emailJson);
            System.out.println(emailJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
