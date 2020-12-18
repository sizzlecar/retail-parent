package com.voyageone.retail.biz.process.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("process-service")
public interface DeploymentClient {

    String DEPLOYMENT_ROOT = "repository";






}
