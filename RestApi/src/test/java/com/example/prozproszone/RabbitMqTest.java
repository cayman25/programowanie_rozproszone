package com.example.prozproszone;

//import com.example.prozproszone.rabbitmq.ReactiveConfiguration;
import com.example.prozproszone.rabbitmq.RabbitMqSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
public class RabbitMqTest {

    @Autowired
    RabbitMqSender rabbitMqSender;

    @Test
    Mono<Void> sendMessage(){
        return null;

    }

}
