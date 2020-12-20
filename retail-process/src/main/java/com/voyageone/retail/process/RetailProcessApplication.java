package com.voyageone.retail.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flowable.rest.service.api.RestResponseFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.voyageone.retail.process", "org.flowable.rest"})
@EnableDiscoveryClient
public class RetailProcessApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailProcessApplication.class, args);
	}

	@Bean
	public RestResponseFactory restResponseFactory() {
		return new RestResponseFactory(new ObjectMapper());
	}

}
