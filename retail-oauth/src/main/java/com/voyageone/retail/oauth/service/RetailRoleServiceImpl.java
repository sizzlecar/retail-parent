package com.voyageone.retail.oauth.service;

import com.voyageone.retail.oauth.dao.RetailRoleMapperExt;
import com.voyageone.retail.oauth.model.RetailRole;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetailRoleServiceImpl implements RetailRoleService{

    private final RetailRoleMapperExt retailRoleMapper;

    public RetailRoleServiceImpl(RetailRoleMapperExt retailRoleMapper) {
        this.retailRoleMapper = retailRoleMapper;
    }


    @HmilyTCC(confirmMethod = "confirmInsertRole", cancelMethod = "cancelInsertRole")
    public String insertRole(RetailRole retailRole){
        if(retailRole == null){
            return "error";
        }
        retailRoleMapper.insert(retailRole);
        return "success";
    }


    public void confirmInsertRole(RetailRole retailRole){
        System.out.println("confirmInsertRole");
    }

    public void cancelInsertRole(RetailRole retailRole){
        RetailRole paraModel = new RetailRole();
        paraModel.setName(retailRole.getName());
        paraModel.setId(retailRole.getId());
        retailRoleMapper.delete(paraModel);
        System.out.println("cancelInsertRole");
    }
}
