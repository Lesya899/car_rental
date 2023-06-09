package org.nik.car_rental.entity;

import java.io.Serializable;

public interface BaseEntity <T extends Serializable> {

    T getId();
    void setId(T id);
}
