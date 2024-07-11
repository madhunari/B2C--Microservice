package com.DeliveryModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DeliveryModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryModuleApplication.class, args);
	}

}
