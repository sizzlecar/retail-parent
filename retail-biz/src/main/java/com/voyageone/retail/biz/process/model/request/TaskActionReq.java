package com.voyageone.retail.biz.process.model.request;

import com.voyageone.retail.biz.process.model.ProcessVariables;
import lombok.Data;

import java.util.List;


@Data
public class TaskActionReq {

    /**
     * 操作 Action to perform: Either complete, claim, delegate or resolve
     */
    private String action;

    /**
     * If action is claim or delegate, you can use this parameter to set the assignee associated
     */
    private String assignee;

    /**
     * Required when completing a task with a form
     */
    private String formDefinitionId;

    /**
     * Optional outcome value when completing a task with a form
     */
    private String outcome;

    private List<ProcessVariables> transientVariables;

    private List<ProcessVariables> variables;


}
