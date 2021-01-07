package com.voyageone.retail.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.voyageone.retail.oauth"})
@EnableDiscoveryClient
public class RetailOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailOauthApplication.class, args);
	}


}
