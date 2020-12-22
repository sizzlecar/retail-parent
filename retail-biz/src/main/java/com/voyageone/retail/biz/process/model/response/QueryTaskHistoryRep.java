package com.voyageone.retail.biz.process.model.response;

import com.voyageone.retail.biz.process.model.ProcessVariables;
import lombok.Data;

import java.util.List;

/**
 * 查询任务历史返回参数model
 */
@Data
public class QueryTaskHistoryRep {

    private String assignee;

    private String category;

    private String claimTime;

    private String deleteReason;

    private String description;

    private String dueDate;

    private Integer durationInMillis;

    private String endTime;

    private String executionId;

    private String formKey;

    private String id;

    private String name;

    private String owner;

    private String parentTaskId;

    private String processDefinitionId;

    private String processInstanceId;

    private String propagatedStageInstanceId;

    private String tenantId;

    private List<ProcessVariables> variables;

    private Integer workTimeInMillis;


}
