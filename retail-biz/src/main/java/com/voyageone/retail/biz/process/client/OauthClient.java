package com.voyageone.retail.biz.process.client;

import com.voyageone.retail.biz.process.model.request.RetailRole;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 调用引擎服务client
 */
@FeignClient(name = "oauth-service")
public interface OauthClient {

    String DEPLOYMENT_ROOT = "/retail-biz/oauth/";

    String ROLE_INSERT = DEPLOYMENT_ROOT +  "role/insert";

    /**
     * 启动一个流程实例
     */
    @PostMapping(path = ROLE_INSERT)
    @Hmily
    String roleInsert(@RequestBody RetailRole req);


}
