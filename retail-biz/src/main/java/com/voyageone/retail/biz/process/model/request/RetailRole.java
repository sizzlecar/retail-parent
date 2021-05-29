package com.voyageone.retail.biz.process.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class RetailRole {

    private Integer id;

    private String name;

    private String creatorId;

    private Date createdTime;

    private String updaterId;

    private Date updatedTime;
}
