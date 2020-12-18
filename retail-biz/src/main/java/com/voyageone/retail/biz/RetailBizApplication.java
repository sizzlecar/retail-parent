package com.voyageone.retail.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.voyageone.retail.biz"})
@EnableDiscoveryClient
public class RetailBizApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailBizApplication.class, args);
	}

}
