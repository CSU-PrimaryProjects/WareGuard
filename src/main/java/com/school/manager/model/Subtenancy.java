package com.school.manager.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

public class Subtenancy {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private Integer e_id;
    private String code;
    private String borrow_out;
    private String borrow_in;
    private String status;
    private String createtime;
    @Transient
    private String superiors;
    @Transient
    private Integer level;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBorrow_out() {
        return borrow_out;
    }

    public void setBorrow_out(String borrow_out) {
        this.borrow_out = borrow_out;
    }

    public String getBorrow_in() {
        return borrow_in;
    }

    public void setBorrow_in(String borrow_in) {
        this.borrow_in = borrow_in;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getSuperiors() {
        return superiors;
    }

    public void setSuperiors(String superiors) {
        this.superiors = superiors;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
