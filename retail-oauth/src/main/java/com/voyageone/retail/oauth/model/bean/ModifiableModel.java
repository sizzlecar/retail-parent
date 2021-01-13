package com.voyageone.retail.oauth.model.bean;

import java.io.Serializable;

public abstract class ModifiableModel<T, D> implements Serializable {

    private T creatorId;
    private D createdTime;
    private T updaterId;
    private D updatedTime;

    public ModifiableModel() {
    }

    public T getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(T creatorId) {
        this.creatorId = creatorId;
    }

    public D getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(D createdTime) {
        this.createdTime = createdTime;
    }

    public T getUpdaterId() {
        return this.updaterId;
    }

    public void setUpdaterId(T updaterId) {
        this.updaterId = updaterId;
    }

    public D getUpdatedTime() {
        return this.updatedTime;
    }

    public void setUpdatedTime(D updatedTime) {
        this.updatedTime = updatedTime;
    }
}
