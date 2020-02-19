package com.shareit.model.entities;

import com.shareit.model.entities.audit.Record;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Post extends Record {

    @Id
    private String _id;

    private String userId;

    private Boolean ordered;

    @Indexed
    private String title;

    @Indexed
    private String desc;

    private List<String> images;

    private PostMeta meta;

    public String getId() {
        return _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public PostMeta getMeta() {
        return meta;
    }

    public void setMeta(PostMeta meta) {
        this.meta = meta;
    }
}
