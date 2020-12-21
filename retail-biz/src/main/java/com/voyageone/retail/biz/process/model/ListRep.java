package com.voyageone.retail.biz.process.model;

import lombok.Data;

import java.util.List;

/**
 * 分页查询容器
 */

@Data
public class ListRep<T> {

    private List<T> data;

    private String order;

    private Integer size;

    private String sort;

    private Integer start;

    private Integer total;
}
