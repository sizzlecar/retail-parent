package com.voyageone.retail.biz.process.model.request;

import lombok.Data;

/**
 * 创建Deployment 参数model
 * @author carl.che
 */
@Data
public class CreateDeploymentReq {

    /**
     * deploymentKey
     */
    private String deploymentKey;

    /**
     * deploymentName
     */
    private String deploymentKeyName;

    /**
     * 上传的文件
     */
    private byte[] file;

    /**
     * 租户id
     */
    private String tenantId;

}
