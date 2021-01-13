package com.voyageone.retail.oauth.model.bean;

import java.util.Date;

public class RetailBaseModel<K> extends ModifiableModel<String, Date>{

    private K id;

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }
}
