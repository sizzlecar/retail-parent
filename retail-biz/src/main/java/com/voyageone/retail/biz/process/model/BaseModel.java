package com.voyageone.retail.biz.process.model;

public class BaseModel<K> {

    private K id;

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }
}
