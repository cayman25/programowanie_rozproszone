package com.example.emailservice.service;

import com.example.emailservice.rabbitmq.RabbitMqReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

@Component
public class RabbitMqConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumerService.class);

    private RabbitMqReceiver rabbitMqReceiver;
    private EmailService emailService;

    @Autowired
    public RabbitMqConsumerService(RabbitMqReceiver rabbitMqReceiver, EmailService emailService) {
        this.rabbitMqReceiver = rabbitMqReceiver;
        this.emailService = emailService;
    }

    public RabbitMqConsumerService() {
    }

    public void consumerRealizeEmail(){
        rabbitMqReceiver.consumerQueue("realize_email")
                .subscribeOn(Schedulers.single())
                .doOnNext(msg -> {
                    LOGGER.info("Receive message from realize_email queue " + new String (msg.getBody()));
                    emailService.sendSimpleMessage(new String (msg.getBody()), "Programowanie Rozproszone Email", "Srutututu COVID-19");
                    LOGGER.info("SENT EMAIL to " + new String (msg.getBody()));
                })
                .doOnError(error -> LOGGER.info(error.getMessage()))
                .subscribe();
    }
}
