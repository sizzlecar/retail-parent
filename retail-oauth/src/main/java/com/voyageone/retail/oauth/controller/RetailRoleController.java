package com.voyageone.retail.oauth.controller;

import com.voyageone.retail.oauth.model.RetailRole;
import com.voyageone.retail.oauth.service.RetailRoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retail-biz/oauth/role")
public class RetailRoleController {

    private final RetailRoleService retailRoleService;

    public RetailRoleController(RetailRoleService retailRoleService) {
        this.retailRoleService = retailRoleService;
    }


    @PostMapping("insert")
    public void insertRole(@RequestBody RetailRole retailRole){
        retailRoleService.insertRole(retailRole);

    }



}
