package com.example.emailservice;

import com.example.emailservice.service.RabbitMqConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class EmailServiceStarter {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceStarter.class);

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceStarter.class, args);
	}

	@Component
	static class Runner implements CommandLineRunner {

		@Autowired
		private RabbitMqConsumerService rabbitMqConsumerService;

		@Override
		public void run(String... args) throws Exception {
			rabbitMqConsumerService.consumerRealizeEmail();
		}
	}
}


