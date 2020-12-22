package com.voyageone.retail.biz.process.constants;

/**
 * URL常量
 */
public interface UrlConstants {

    String ROOT = "/retail-biz/";


    /**
     * 流程相关接口
     */
    interface Process{

        String ROOT = UrlConstants.ROOT + "process/";

        /**
         * 发布流程
         */
        String DEPLOYMENT = "deployment";

        /**
         * 启动流程
         */
        String START = "start";

        /**
         * 查询任务
         */
        String QUERY_TASK = "query-task";

        /**
         * 对task进行操作
         */
        String ACTION_TASK = "{taskId}";

        /**
         * 查询任务历史
         */
        String TASK_HISTORY = "historic-task-instances";

    }




}
