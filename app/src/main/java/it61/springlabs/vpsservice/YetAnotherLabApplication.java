package it61.springlabs.vpsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class YetAnotherLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(YetAnotherLabApplication.class, args);
	}

}
