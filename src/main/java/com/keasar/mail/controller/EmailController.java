package com.keasar.mail.controller;


import com.keasar.mail.service.EmailProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor

@RestController
public class EmailController {
    private final EmailProducer emailProducer;

    @PostMapping("/send-email")
    public void sendEmail(@RequestBody String emailDetails) {
        emailProducer.sendEmailMessage(emailDetails);
    }
}

