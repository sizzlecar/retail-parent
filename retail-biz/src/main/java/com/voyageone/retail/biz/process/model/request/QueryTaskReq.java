package com.voyageone.retail.biz.process.model.request;


import lombok.Data;

import java.util.List;

/**
 * 查询task参数model
 */
@Data
public class QueryTaskReq {

    private Boolean active;

    private String assignee;

    private String assigneeLike;

    private String candidateGroup;

    private List<String> candidateGroupIn;

    private String processDefinitionId;

    private String processDefinitionKey;

    private String processInstanceBusinessKey;

    private String processInstanceId;

    private String tenantId;


}
