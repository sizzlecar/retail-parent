package com.voyageone.retail.biz.process.controller;

import com.voyageone.retail.biz.process.client.DeploymentClient;
import com.voyageone.retail.biz.process.model.request.CreateDeploymentReq;
import com.voyageone.retail.biz.process.model.response.CreateDeploymentRep;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCallController {

    private final DeploymentClient deploymentClient;

    public TestCallController(DeploymentClient deploymentClient) {
        this.deploymentClient = deploymentClient;
    }



    @PostMapping("deployment")
    public String createDeployment() throws Exception{
        CreateDeploymentReq createDeploymentReq = new CreateDeploymentReq();
        createDeploymentReq.setDeploymentKey(System.currentTimeMillis() + "test");
        createDeploymentReq.setDeploymentKeyName("demo001");
        createDeploymentReq.setTenantId("demo");
        createDeploymentReq.setFile(IOUtils.toByteArray(getClass().getResourceAsStream("/holiday-request.bpmn20.xml")));
        CreateDeploymentRep deployment = deploymentClient.createDeployment(createDeploymentReq);
        return deployment.getId();
    }
}
