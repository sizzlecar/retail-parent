package com.voyageone.retail.biz.process.controller;

import com.voyageone.retail.biz.process.model.response.CreateDeploymentRep;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateDeploymentController {

    private final RestTemplate restTemplate;

    public RestTemplateDeploymentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("deployment2")
    public String createDeployment() throws Exception{
        HttpHeaders headers = new HttpHeaders();
        FileSystemResource fileSystemResource = new FileSystemResource(RestTemplateDeploymentController.class.getResource("/holiday-request.bpmn20.xml").getPath());
        MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("deploymentKey", System.currentTimeMillis() + "test");
        valueMap.add("deploymentKeyName", "demo001");
        valueMap.add("file", fileSystemResource);
        valueMap.add("fileName", "holiday-request.bpmn20.xml");
        valueMap.add("tenantId", "demo");
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(valueMap, headers);
        CreateDeploymentRep object = restTemplate.postForObject("http://process-service/repository/deployments", entity, CreateDeploymentRep.class);
        assert object != null;
        return object.getId();
    }



}
