package com.voyageone.retail.oauth.service;

import com.voyageone.retail.oauth.dao.RetailRoleMapper;
import com.voyageone.retail.oauth.model.RetailRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetailRoleService {

    private final RetailRoleMapper retailRoleMapper;

    public RetailRoleService(RetailRoleMapper retailRoleMapper) {
        this.retailRoleMapper = retailRoleMapper;
    }


    public int insertRole(RetailRole retailRole){
        if(retailRole == null){
            return 0;
        }
        return retailRoleMapper.insert(retailRole);
    }
}
