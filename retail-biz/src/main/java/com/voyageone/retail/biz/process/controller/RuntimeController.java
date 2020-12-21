package com.voyageone.retail.biz.process.controller;

import com.voyageone.retail.biz.process.client.ProcessClient;
import com.voyageone.retail.biz.process.model.request.StartProcessInstanceReq;
import com.voyageone.retail.biz.process.model.response.StartProcessInstanceRep;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuntimeController {

    private final ProcessClient processClient;

    public RuntimeController(ProcessClient runtimeClient) {
        this.processClient = runtimeClient;
    }

    @PostMapping("startProcess")
    public String startProcess() {
        StartProcessInstanceReq startProcessInstanceReq = new StartProcessInstanceReq();
        startProcessInstanceReq.setProcessDefinitionKey("test");
        startProcessInstanceReq.setReturnVariables(true);
        StartProcessInstanceRep processInstance = processClient.startProcessInstance(startProcessInstanceReq);
        assert processInstance != null;
        return processInstance.getId();
    }


}
