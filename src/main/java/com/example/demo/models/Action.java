package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by student on 7/12/17.
 */
@Entity
public class Action {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long id_Photo;
    private long id_Usr;
    private String typeAction;
    private String content;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_Photo() {
        return id_Photo;
    }

    public void setId_Photo(long id_Photo) {
        this.id_Photo = id_Photo;
    }

    public long getId_Usr() {
        return id_Usr;
    }

    public void setId_Usr(long id_Usr) {
        this.id_Usr = id_Usr;
    }

    public String getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(String typeAction) {
        this.typeAction = typeAction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
