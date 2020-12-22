package com.voyageone.retail.biz.process.model.request;

import com.voyageone.retail.biz.process.model.ProcessVariables;
import lombok.Data;

import java.util.List;

/**
 * 启动流程参数model
 */
@Data
public class StartProcessInstanceReq {

    private String businessKey;

    private String message;

    private String name;

    private String outcome;

    private String overrideDefinitionTenantId;

    private String processDefinitionId;

    /**
     * 流程定义的key,对应xml中 process标签的id,如果存在多个版本，则启动最新的版本
     */
    private String processDefinitionKey;

    private Boolean returnVariables;

    private List<ProcessVariables> startFormVariables;

    private String tenantId;

    private List<ProcessVariables> transientVariables;

    private List<ProcessVariables> variables;



}
