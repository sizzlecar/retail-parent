package com.voyageone.retail.biz.process.model.response;

import lombok.Data;

@Data
public class CreateDeploymentRep {

    /**
     * 分类标识
     */
    private String category;

    /**
     * 发布时间
     */
    private String deploymentTime;

    /**
     * id
     */
    private String id;

    /**
     * 名字
     */
    private String name;

    /**
     * 父id
     */
    private String parentDeploymentId;

    /**
     * 租户id
     */
    private String tenantId;


    /**
     * url
     */
    private String url;
}
