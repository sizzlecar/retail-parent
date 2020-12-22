package com.voyageone.retail.biz.process.model.request;


import lombok.Data;

import java.util.List;

/**
 * 查询task参数model
 */
@Data
public class QueryTaskReq {

    private Boolean active;

    /**
     * 指定用户
     */
    private String assignee;

    private String assigneeLike;

    /**
     * 指定用户组
     */
    private String candidateGroup;

    private List<String> candidateGroupIn;

    /**
     * 流程定义id
     */
    private String processDefinitionId;

    private String processDefinitionKey;

    private String processInstanceBusinessKey;

    /**
     * 流程实例id
     */
    private String processInstanceId;

    /**
     * 租户id
     */
    private String tenantId;


}
