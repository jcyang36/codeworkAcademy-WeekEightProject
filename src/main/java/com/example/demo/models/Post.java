package com.example.demo.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by student on 7/13/17.
 */
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String imageUrl;
    private String content;
    private int userId;

    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "post_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }



}
