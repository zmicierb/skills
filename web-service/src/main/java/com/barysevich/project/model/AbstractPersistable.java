package com.barysevich.project.model;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;

/**
 * Created by BarysevichD on 2017-04-07.
 */
public abstract class AbstractPersistable<ID extends Serializable> implements Persistable<ID> {

    public abstract void setId(ID id);

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AbstractPersistable that = (AbstractPersistable) o;
        return !(getId() != null ? !getId().equals(that.getId()) : that.getId() != null);
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public boolean isNew() {
        return this.getId() == null;
    }
}
