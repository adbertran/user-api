package com.api.dtos;

import com.api.domain.UserDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserJson  extends AbstractJson{
    @JsonProperty("userid")
    private Integer userId;
    private String name;
    private String lastName;
    @JsonProperty("email")
    private String eMail;
    @JsonProperty("imageurl")
    private String imageUrl;
    private String password;
    @JsonFormat (shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;

    public static UserJson createFrom(UserDomain userDomain) {
        UserJson userJson = new UserJson();

        userJson.userId= userDomain.getUserId();
        userJson.name= userDomain.getName();
        userJson.lastName= userDomain.getLastName();
        userJson.eMail= userDomain.geteMail();
        userJson.imageUrl= userDomain.getImageUrl();
        userJson.password= userDomain.getPassword();
        userJson.createdAt=userDomain.getCreatedAt();

        return userJson;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
