package com.voyageone.retail.biz.process.controller;

import com.voyageone.retail.biz.process.client.ProcessClient;
import com.voyageone.retail.biz.process.model.ListRep;
import com.voyageone.retail.biz.process.model.ProcessVariables;
import com.voyageone.retail.biz.process.model.request.QueryTaskReq;
import com.voyageone.retail.biz.process.model.request.TaskActionReq;
import com.voyageone.retail.biz.process.model.response.CreateDeploymentRep;
import com.voyageone.retail.biz.process.model.response.QueryTaskRep;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class FeignDeploymentController {

    private ProcessClient processClient;

    public FeignDeploymentController(ProcessClient processClient) {
        this.processClient = processClient;
    }

    @PostMapping("deployment2")
    public String createDeployment2() {
        FileSystemResource fileSystemResource = new FileSystemResource(RestTemplateDeploymentController.class.getResource("/demo-001.bpmn20.xml").getPath());
        MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("deploymentKey", "20201221demo001");
        valueMap.add("deploymentKeyName", "20201221demo001");
        valueMap.add("file", fileSystemResource);
        valueMap.add("fileName", "demo-001.bpmn20.xml");
        CreateDeploymentRep object = processClient.createDeployment(valueMap);
        assert object != null;
        return object.getId();
    }

    @PostMapping("query-task")
    public String queryTask(){
        QueryTaskReq queryTaskReq = new QueryTaskReq();
        queryTaskReq.setActive(true);
        queryTaskReq.setProcessInstanceId("9c68f9fd-4367-11eb-87a9-8cec4b77b5ef");
        queryTaskReq.setCandidateGroup("managers");
        ListRep<QueryTaskRep> listRep = processClient.queryTask(queryTaskReq);
        assert listRep != null;
        assert listRep.getData().get(0) != null;
        return listRep.getData().get(0).getId();
    }

    @PostMapping("claim-task")
    public void claimTask(){
        TaskActionReq queryTaskReq = new TaskActionReq();
        queryTaskReq.setAction("claim");
        queryTaskReq.setOutcome("认领任务");
        queryTaskReq.setAssignee("managers-001");
        processClient.taskAction("4f956b2f-437c-11eb-bda4-8cec4b77b5ef", queryTaskReq);

    }

    @PostMapping("complete-task")
    public void completeTask(){
        TaskActionReq queryTaskReq = new TaskActionReq();
        queryTaskReq.setAction("complete");
        queryTaskReq.setOutcome("完成任务");
        ProcessVariables processVariables = new ProcessVariables();
        processVariables.setName("approved");
        processVariables.setValue(true);
        queryTaskReq.setVariables(Collections.singletonList(processVariables));
        processClient.taskAction("4f956b2f-437c-11eb-bda4-8cec4b77b5ef", queryTaskReq);

    }






}
