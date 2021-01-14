package com.voyageone.retail.oauth.test;


import com.voyageone.retail.oauth.dao.RetailRoleMapper;
import com.voyageone.retail.oauth.model.RetailRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetailRoleMapperTest {


    @Autowired
    RetailRoleMapper retailRoleMapper;




    @Test
    public void selectRoleTest(){
        RetailRole retailRole = new RetailRole();
        retailRole.setName("admin");
        retailRole.setCreatedTime(new Date());
        retailRole.setUpdatedTime(new Date());
        retailRole.setCreatorId("admin");
        retailRole.setUpdaterId("admin");
        retailRoleMapper.insert(retailRole);

        System.out.println(retailRole.getId());

    }




}
