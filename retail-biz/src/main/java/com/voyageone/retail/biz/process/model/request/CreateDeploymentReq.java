package com.voyageone.retail.biz.process.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * 创建Deployment 参数model
 * @author carl.che
 */
@Data
public class CreateDeploymentReq {

    /**
     * deploymentKey
     */
    @NotNull(message = "deploymentKey不能为空")
    private String deploymentKey;

    /**
     * deploymentName
     */
    @NotNull(message = "deploymentKeyName不能为空")
    private String deploymentKeyName;

    /**
     * 上传的文件
     */
    @NotNull(message = "流程定义文件不能为空")
    private MultipartFile file;

    /**
     * 租户id
     */
    private String tenantId;

}
