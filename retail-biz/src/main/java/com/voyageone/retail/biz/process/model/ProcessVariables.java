package com.voyageone.retail.biz.process.model;


import lombok.Data;

/**
 * 流程变量定义
 */
@Data
public class ProcessVariables {

    private String name;

    private String scope;

    private String type;

    private Object value;

    private String valueUrl;


}
