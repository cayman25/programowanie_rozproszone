package com.example.emailservice.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Service
public class EmailConfiguration {

    @Value("${email_host}")
    private String hostname;

    @Value("${email_port}")
    private String port;

    @Value("${email_username}")
    private String username;

    @Value("${email_password}")
    private String password;

    public JavaMailSenderImpl javaMailSender;

    private void setJavaMailSender(){
        this.javaMailSender = new JavaMailSenderImpl();
        this.javaMailSender.setHost(hostname);
        this.javaMailSender.setPort(Integer.valueOf(port));
        this.javaMailSender.setUsername(username);
        this.javaMailSender.setPassword(password);

        Properties props = this.javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");
    }

    @PostConstruct
    void init(){
        setJavaMailSender();
    }

}
