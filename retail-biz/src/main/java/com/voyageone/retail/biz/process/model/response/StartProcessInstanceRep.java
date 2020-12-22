package com.voyageone.retail.biz.process.model.response;

import com.voyageone.retail.biz.process.model.ProcessVariables;
import lombok.Data;

import java.util.List;

/**
 * 启动流程 response
 */
@Data
public class StartProcessInstanceRep {

    private String activityId;

    private String businessKey;

    private String callbackId;

    private String callbackType;

    private Boolean completed;

    private Boolean ended;

    /**
     * 流程实例id
     */
    private String id;

    private String name;

    private String processDefinitionDescription;

    private String processDefinitionId;

    private String processDefinitionName;

    private String processDefinitionUrl;

    private String referenceId;

    private String referenceType;

    private String startTime;

    private String startUserId;

    private Boolean suspended;

    private String tenantId;

    private String url;

    private List<ProcessVariables> variables;

    private Integer workTimeInMillis;

}
