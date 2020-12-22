package com.voyageone.retail.biz.process.controller;

import com.voyageone.retail.biz.process.client.ProcessClient;
import com.voyageone.retail.biz.process.constants.UrlConstants;
import com.voyageone.retail.biz.process.model.FileByteArrayResource;
import com.voyageone.retail.biz.process.model.ListRep;
import com.voyageone.retail.biz.process.model.request.*;
import com.voyageone.retail.biz.process.model.response.CreateDeploymentRep;
import com.voyageone.retail.biz.process.model.response.QueryTaskHistoryRep;
import com.voyageone.retail.biz.process.model.response.QueryTaskRep;
import com.voyageone.retail.biz.process.model.response.StartProcessInstanceRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 模拟业务调用引擎服务接口 controller
 */
@RestController
@RequestMapping(UrlConstants.Process.ROOT)
@Slf4j
public class ProcessController {

    private ProcessClient processClient;



    public ProcessController(ProcessClient processClient) {
        this.processClient = processClient;
    }

    /**
     * 发布新的流程
     */
    @PostMapping(UrlConstants.Process.DEPLOYMENT)
    public CreateDeploymentRep createDeployment(@Validated CreateDeploymentReq createDeploymentReq) throws IOException {
        MultipartFile file = createDeploymentReq.getFile();
        byte[] array = null;
        try {
            array = file.getBytes();
        }catch (IOException e){
            log.error("获取字节数据发生错误", e);
            return null;
        }

        MultiValueMap<String, Object> valueMap  = new LinkedMultiValueMap<>();
        valueMap.add("deploymentKey", createDeploymentReq.getDeploymentKey());
        valueMap.add("deploymentKeyName", createDeploymentReq.getDeploymentKeyName());
        valueMap.add("file", new FileByteArrayResource(array, "bpmn20.xml", file.getOriginalFilename()));
        return processClient.createDeployment(valueMap);
    }

    /**
     * 查询任务
     */
    @PostMapping(UrlConstants.Process.QUERY_TASK)
    public ListRep<QueryTaskRep> queryTask(@RequestBody QueryTaskReq queryTaskReq){
        return processClient.queryTask(queryTaskReq);
    }

    /**
     * 对任务进行操作
     */
    @PostMapping(UrlConstants.Process.ACTION_TASK)
    public void completeTask(@PathVariable("taskId") String taskId, @RequestBody TaskActionReq req){
        processClient.taskAction(taskId, req);
    }

    /**
     * 查询任务历史
     */
    @PostMapping(UrlConstants.Process.TASK_HISTORY)
    public ListRep<QueryTaskHistoryRep> queryTaskHistory(@RequestBody QueryTaskHistoryReq historyReq){
        return processClient.queryTaskHistory(historyReq);
    }


    /**
     * 启动流程
     */
    @PostMapping(UrlConstants.Process.START)
    public StartProcessInstanceRep startProcess(@RequestBody StartProcessInstanceReq startProcessInstanceReq) {
        return processClient.startProcessInstance(startProcessInstanceReq);
    }



}
