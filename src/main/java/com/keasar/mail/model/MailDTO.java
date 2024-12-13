package com.keasar.mail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class MailDTO {
    private String target ;
    private String emailSubject ;
    private  String Body ;

}
