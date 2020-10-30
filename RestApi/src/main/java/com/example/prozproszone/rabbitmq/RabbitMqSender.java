package com.example.prozproszone.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.*;

@Service
public class RabbitMqSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqSender.class);
    private final Sender sender;

    public RabbitMqSender() {
        this.sender = RabbitFlux.createSender();
    }

    public Mono<Void> sendMono(String queue, String message) {
        return Mono.just(sender.declareQueue(QueueSpecification.queue(queue))
                .then(sender.sendWithPublishConfirms(Mono.just(new OutboundMessage( "", queue, message.getBytes()))).then())
                .doOnError(e -> LOGGER.error("Send failed", e))
                .doOnSuccess(r -> LOGGER.info("Message " + message + " sent successfully to queue " + queue))
                .subscribe())
                .then();
    }

    public void close() {
        this.sender.close();
    }
}
