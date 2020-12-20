package com.voyageone.retail.biz.process.controller;

import com.voyageone.retail.biz.process.client.DeploymentClient;
import com.voyageone.retail.biz.process.model.request.CreateDeploymentReq;
import com.voyageone.retail.biz.process.model.response.CreateDeploymentRep;
import org.apache.commons.io.IOUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestCallController {

    private final RestTemplate restTemplate;

    public TestCallController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("deployment")
    public String createDeployment() throws Exception{
        CreateDeploymentReq createDeploymentReq = new CreateDeploymentReq();
        createDeploymentReq.setDeploymentKey(System.currentTimeMillis() + "test");
        createDeploymentReq.setDeploymentKeyName("demo001");
        createDeploymentReq.setTenantId("demo");
        createDeploymentReq.setFile(IOUtils.toByteArray(getClass().getResourceAsStream("/holiday-request.bpmn20.xml")));
        Map<String, Object> map = new HashMap<>();
        map.put("deploymentKey", System.currentTimeMillis() + "test");
        map.put("deploymentKeyName", "demo001");
        map.put("tenantId", "demo");
        map.put("file", IOUtils.toByteArray(getClass().getResourceAsStream("/holiday-request.bpmn20.xml")));
        CreateDeploymentRep object = restTemplate.getForObject("http://process-service/repository/deployments", CreateDeploymentRep.class, map);
        assert object != null;
        return object.getId();
    }
}
