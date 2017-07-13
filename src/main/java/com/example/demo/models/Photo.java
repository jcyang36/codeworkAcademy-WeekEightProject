package com.example.demo.models;

import javax.persistence.*;
import java.util.Set;

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


    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable (joinColumns = @JoinColumn(name = "photo_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
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
