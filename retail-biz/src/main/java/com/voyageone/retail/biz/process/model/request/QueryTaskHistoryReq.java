package com.voyageone.retail.biz.process.model.request;

import lombok.Data;

/**
 * 查询历史
 */
@Data
public class QueryTaskHistoryReq {

    private String dueDate;

    private String dueDateAfter;

    private String dueDateBefore;

    private String executionId;

    private Boolean finished;

    private Boolean ignoreTaskAssignee;

    private Boolean includeProcessVariables;

    private Boolean includeTaskLocalVariables;

    private String order;

    private String processBusinessKey;

    private String processDefinitionId;

    private String processDefinitionKey;

    private String processDefinitionName;

    private Boolean processFinished;

    private String processInstanceId;

    private String taskId;

    private String tenantId;

    private Integer size;

    private String sort;

    private Integer start;


}
