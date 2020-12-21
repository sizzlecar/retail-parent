package com.voyageone.retail.biz.process.model.response;


import com.voyageone.retail.biz.process.model.ProcessVariables;
import lombok.Data;

import java.util.List;

/**
 * 查询task rep
 */
@Data
public class QueryTaskRep {

    private String assignee;

    private String category;

    private String delegationState;

    private String description;

    private String executionId;

    private String executionUrl;

    private String formKey;

    private String id;

    private String processDefinitionId;

    private String processInstanceId;

    private String propagatedStageInstanceId;

    private String tenantId;

    private List<ProcessVariables> variables;

}
