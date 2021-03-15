package com.school.manager.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

public class Borrow {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private Integer user_id;
    private Integer equipment_id;
    private String borrow_date;
    private String start_date;
    private String e_status;
    private String damage;
    private String reason;
    @Transient
    private String name;
    @Transient
    private Integer num;

    public String getE_status() {
        return e_status;
    }

    public void setE_status(String e_status) {
        this.e_status = e_status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
