package com.api.domain;

import com.api.dtos.UserJson;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "user")
public class UserDomain {
    private Integer userId;
    private String name;
    private String lastName;
    private String eMail;
    private String imageUrl;
    private String password;
    private transient Timestamp createdAt;

    @Id
    @Column(name="user_id",nullable = false)
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name="name",nullable = false,length = 50)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name="last_name",nullable = false,length = 50)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name="email",nullable = false,length = 50)
    public String geteMail() {
        return eMail;
    }
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name="image_url",nullable = false,length = 100)
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name="password",nullable = false,length = 12)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name="created_at",nullable = false,updatable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(new Date().getTime());
    }


    public static UserDomain createFrom(UserJson userJson) {
        UserDomain userDomain = new UserDomain();

        userDomain.userId = userJson.getUserId();
        userDomain.name = userJson.getName();
        userDomain.lastName = userJson.getLastName();
        userDomain.eMail = userJson.geteMail();
        userDomain.imageUrl = userJson.getImageUrl();
        userDomain.password = userJson.getPassword();
        userDomain.createdAt = userJson.getCreatedAt();

        return userDomain;
    }
}
