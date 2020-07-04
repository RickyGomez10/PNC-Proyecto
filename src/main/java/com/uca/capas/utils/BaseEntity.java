package com.uca.capas.utils;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    protected transient boolean persisted;


    @PostLoad
    public void postLoad() {
        this.persisted = true;
    }

    @PostUpdate
    public void postUpdate() {
        this.persisted = true;
    }

    @PostPersist
    public void postPersist() {
        this.persisted = true;
    }

}
