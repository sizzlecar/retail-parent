package com.voyageone.retail.biz.process.client;

import com.voyageone.retail.biz.process.model.request.CreateDeploymentReq;
import com.voyageone.retail.biz.process.model.response.CreateDeploymentRep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("process-service")
public interface DeploymentClient {

    String DEPLOYMENT_ROOT = "/repository/";



    @PostMapping(path = DEPLOYMENT_ROOT + "deployments", headers = "multipart/form-data")
    CreateDeploymentRep createDeployment(@RequestBody CreateDeploymentReq createDeploymentReq);



}
