package com.voyageone.retail.oauth.dao;

import com.voyageone.retail.oauth.model.RetailRole;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailRoleMapperExt extends RetailRoleMapper{

    int delete(RetailRole retailRole);
}