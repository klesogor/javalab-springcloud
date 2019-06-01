package it61.springlabs.vpsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HostingQueueConsumer {

	public static void main(String[] args) {
		SpringApplication.run(HostingQueueConsumer.class, args);
	}
}
