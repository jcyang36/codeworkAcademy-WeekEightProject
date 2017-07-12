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
    private long idPhoto;
    private long idUsr;
    private String typeAction;
    private String content;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(long idPhoto) {
        this.idPhoto = idPhoto;
    }

    public long getIdUsr() {
        return idUsr;
    }

    public void setIdUsr(long idUsr) {
        this.idUsr = idUsr;
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
