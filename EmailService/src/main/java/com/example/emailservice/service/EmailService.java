package com.example.emailservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private EmailConfiguration emailConfiguration;

    @Autowired
    public EmailService(EmailConfiguration emailConfiguration) {
        this.emailConfiguration = emailConfiguration;
    }

    void realizeRegisterEmail(byte[] message){
        LOGGER.info("Try send email to " + new String(message));
    }

    public void sendSimpleMessage(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("drobotm95@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        JavaMailSenderImpl javaMailSender = emailConfiguration.javaMailSender;
        javaMailSender.send(message);
    }
}
