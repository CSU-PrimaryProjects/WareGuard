package com.school.manager.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class CheckRecord {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private Integer e_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getE_id() {
        return e_id;
    }

    public void setE_id(Integer e_id) {
        this.e_id = e_id;
    }
}
