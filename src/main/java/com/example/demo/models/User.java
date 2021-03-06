package com.example.demo.models;

/**
 * Created by student on 7/5/17.
 */

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "userData")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "password")
    @org.springframework.data.annotation.Transient
    private String password;

    @Column(name = "roleName")
    @Size(max=50)
    @NotNull
    private String roleName;


    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "username")
    private String username;


    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable (joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Collection<Post> posts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;



    public User(String email, String password, boolean enabled, String username) {
        this.email = email;
        this.password = password;

        this.enabled = enabled;
        this.username = username;
    }



    public User() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}