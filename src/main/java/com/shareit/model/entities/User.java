package com.shareit.model.entities;

import com.shareit.model.entities.audit.Record;
import com.shareit.security.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class User extends Record {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private List<Role> roles;

    private Account account;

    private Profile profile;

    @DBRef
    private List<Post> posts;

    @DBRef
    private Board board;

    public Post appendPost(Post post) {
        if (posts == null)
            posts = new ArrayList<>();
        posts.add(post);
        return post;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
