package com.example.emailservice.rabbitmq;

import com.rabbitmq.client.Delivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

@Service
public class RabbitMqReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqReceiver.class);

    private final Receiver receiver;

    public RabbitMqReceiver() {
        this.receiver = RabbitFlux.createReceiver();
    }

    public Flux<Delivery> consumerQueue(String queue){
        return receiver.consumeAutoAck(queue)
                .doOnSubscribe(subscription -> LOGGER.info("Success subscribe to queue " + queue))
                .doOnError(error -> LOGGER.error("Error during subscribe to " + queue + " " + error.getMessage()))
                .doOnNext(m -> LOGGER.info("Sent message to queue subscriber"));
    }

    public void close() {
        this.receiver.close();
    }
}
