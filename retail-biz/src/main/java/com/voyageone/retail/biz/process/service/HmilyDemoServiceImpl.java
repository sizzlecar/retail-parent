package com.voyageone.retail.biz.process.service;

import com.voyageone.retail.biz.process.client.OauthClient;
import com.voyageone.retail.biz.process.model.RbLog;
import com.voyageone.retail.biz.process.model.request.RetailRole;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class HmilyDemoServiceImpl implements HmilyDemoService{

    private final OauthClient oauthClient;
    private final RbLogService rbLogService;

    public HmilyDemoServiceImpl(OauthClient oauthClient,
                                RbLogService rbLogService) {
        this.oauthClient = oauthClient;
        this.rbLogService = rbLogService;
    }


    @HmilyTCC(confirmMethod = "confirmCreateRole", cancelMethod = "cancelCreateRole")
    public String createRole(String name) {
        RetailRole retailRole = new RetailRole();
        retailRole.setCreatedTime(new Date());
        retailRole.setUpdatedTime(new Date());
        retailRole.setCreatorId("biz");
        retailRole.setUpdaterId("biz");
        retailRole.setName(name);
        oauthClient.roleInsert(retailRole);
        RbLog rbLog = new RbLog();
        rbLog.setCreateTime(new Date());
        rbLog.setOp(name);
        rbLogService.insertLog(rbLog);
        return "success";
    }

    public void confirmCreateRole(String name){
        System.out.println("confirm");
        //throw new RuntimeException("confirm error");
    }


    public void cancelCreateRole(String name){
        System.out.println("cancel");
        RbLog rbLog = new RbLog();
        rbLog.setOp(name);
        rbLogService.deleteLog(rbLog);
    }
}
