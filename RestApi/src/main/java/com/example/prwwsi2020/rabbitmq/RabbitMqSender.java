package com.example.prwwsi2020.rabbitmq;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.*;

@Log4j2
@Service
public class RabbitMqSender {

    private final Sender sender;

    public RabbitMqSender() {
        this.sender = RabbitFlux.createSender();
    }

    public Mono<Void> sendMono(String queue, String message) {
        return Mono.just(sender.declareQueue(QueueSpecification.queue(queue))
                .then(sender.sendWithPublishConfirms(Mono.just(new OutboundMessage( "", queue, message.getBytes()))).then())
                .doOnError(e -> log.error("Send failed", e))
                .doOnSuccess(r -> log.info("Message " + message + " sent successfully to queue " + queue))
                .subscribe())
                .then();
    }

    public void close() {
        this.sender.close();
    }
}

