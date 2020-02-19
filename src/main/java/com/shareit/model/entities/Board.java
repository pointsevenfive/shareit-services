package com.shareit.model.entities;

import com.shareit.model.entities.audit.Record;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Board extends Record {

    @Id
    private String id;

    @DBRef
    private List<Post> posts;

    public String getId() {
        return id;
    }

    public void addPostToBoard(Post post) {
        if (posts == null)
            posts = new ArrayList<>();
        posts.add(post);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
