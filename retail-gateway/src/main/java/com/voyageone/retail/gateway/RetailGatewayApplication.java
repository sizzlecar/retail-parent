package com.voyageone.retail.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.voyageone.retail.gateway"})
@EnableDiscoveryClient
public class RetailGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetailGatewayApplication.class, args);
    }
}
