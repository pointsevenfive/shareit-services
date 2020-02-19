package com.shareit.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shareit.model.entities.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Document
public class TrustedUser {

    @Id
    private String id;

    @Indexed(unique = true)
    @Email
    private String username;

    @NotBlank
    @Length(min = 8)
    private String authPhrase;

    @DBRef
    @JsonIgnoreProperties
    private User user;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthPhrase() {
        return authPhrase;
    }

    public void setAuthPhrase(String authPhrase) {
        this.authPhrase = authPhrase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
