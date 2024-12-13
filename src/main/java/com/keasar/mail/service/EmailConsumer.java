package com.keasar.mail.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.keasar.mail.model.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    @JmsListener(destination = "emailQueue")
    public void receiveEmail(String emailJson) {
        try {
            // Deserialize the JSON string to a MailDTO object
            MailDTO emailDetails = objectMapper.readValue(emailJson, MailDTO.class);

            String to = emailDetails.getTarget();
            String subject = emailDetails.getEmailSubject();  // You may want to rename this field as mentioned above
            String body = emailDetails.getBody();

            emailService.sendEmail(to, subject, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
