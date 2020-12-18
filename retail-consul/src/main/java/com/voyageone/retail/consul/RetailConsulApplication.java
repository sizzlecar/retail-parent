package com.voyageone.retail.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.voyageone.retail.consul"})
@EnableDiscoveryClient
@EnableScheduling
public class RetailConsulApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailConsulApplication.class, args);
	}

}
