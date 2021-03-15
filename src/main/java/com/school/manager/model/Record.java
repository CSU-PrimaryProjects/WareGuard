package com.school.manager.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Record {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private Integer user_id;
    private Integer book_id;
    private String status;
    private String createtime;

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
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
}
