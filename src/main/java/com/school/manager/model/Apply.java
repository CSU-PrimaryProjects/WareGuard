package com.school.manager.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Apply {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String name;
    private String path;
    private String publicationtime;
    private String createperson;
    private String autor;
    private Integer num;
    private String publisher;

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPath(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }

    public void setCreateperson(String createperson){
        this.createperson = createperson;
    }

    public String getCreateperson(){
        return createperson;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPublicationtime() {
        return publicationtime;
    }

    public void setPublicationtime(String publicationtime) {
        this.publicationtime = publicationtime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
