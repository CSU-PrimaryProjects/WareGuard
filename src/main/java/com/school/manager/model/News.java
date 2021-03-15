package com.school.manager.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class News {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String title;
    private String digest;
    private String content;
    private String createtime;
    private String createperson;

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }


    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatetime(String createtime){
        this.createtime = createtime;
    }

    public String getCreatetime(){
        return createtime;
    }

    public void setCreateperson(String createperson){
        this.createperson = createperson;
    }

    public String getCreateperson(){
        return createperson;
    }


}
