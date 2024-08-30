package com.belle_bliss_fashions_user_service_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BelleBlissFashionsUserService1Application {

	public static void main(String[] args) {
		SpringApplication.run(BelleBlissFashionsUserService1Application.class, args);
	}

}
