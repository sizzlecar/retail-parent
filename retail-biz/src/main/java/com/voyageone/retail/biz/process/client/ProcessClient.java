package com.voyageone.retail.biz.process.client;

import com.voyageone.retail.biz.process.model.ListRep;
import com.voyageone.retail.biz.process.model.request.QueryTaskHistoryReq;
import com.voyageone.retail.biz.process.model.request.QueryTaskReq;
import com.voyageone.retail.biz.process.model.request.StartProcessInstanceReq;
import com.voyageone.retail.biz.process.model.request.TaskActionReq;
import com.voyageone.retail.biz.process.model.response.CreateDeploymentRep;
import com.voyageone.retail.biz.process.model.response.QueryTaskHistoryRep;
import com.voyageone.retail.biz.process.model.response.QueryTaskRep;
import com.voyageone.retail.biz.process.model.response.StartProcessInstanceRep;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 调用引擎服务client
 */
@FeignClient(name = "process-service", configuration = ProcessClient.MultipartSupportConfig.class)
public interface ProcessClient {

    String DEPLOYMENT_ROOT = "/repository/";

    String RUNTIME_ROOT = "/runtime/process-instances";

    String RUNTIME_TASKS = "/runtime/tasks/";

    String QUERY = "/query/";

    /**
     * 启动一个流程实例
     */
    @PostMapping(path = RUNTIME_ROOT)
    StartProcessInstanceRep startProcessInstance(@RequestBody StartProcessInstanceReq req);

    /**
     * 查询任务
     */
    @PostMapping(path = QUERY + "tasks")
    ListRep<QueryTaskRep> queryTask(@RequestBody QueryTaskReq req);


    /**
     * 对任务进行操作 如: complete(完成), claim(认领)
     */
    @PostMapping(path = RUNTIME_TASKS + "{taskId}")
    void taskAction(@PathVariable("taskId") String taskId, @RequestBody TaskActionReq req);



    /**
     * 发布一个新的流程
     */
    @PostMapping(path = DEPLOYMENT_ROOT + "deployments", headers = "multipart/form-data")
    CreateDeploymentRep createDeployment(MultiValueMap<String, Object> valueMap);


    /**
     * 查询任务历史
     */
    @PostMapping(path = QUERY + "historic-task-instances")
    ListRep<QueryTaskHistoryRep> queryTaskHistory(QueryTaskHistoryReq queryTaskHistoryReq);



    /**
     * 支持表单文件上传配置
     */
    class MultipartSupportConfig {

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignFormEncoder () {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }


}
