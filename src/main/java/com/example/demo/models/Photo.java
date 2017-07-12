package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by student on 7/10/17.
 */
@Entity
public class Photo {



    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String photosrc;
    private String caption;
    private String photoname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPhotosrc() {
        return photosrc;
    }

    public void setPhotosrc(String photosrc) {
        this.photosrc = photosrc;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPhotoname() {
        return photoname;
    }

    public void setPhotoname(String photoname) {
        this.photoname = photoname;
    }

}
