package com.shareit.model.entities;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Tags {

    @Id
    private String _id;

    private List<Tag> category;

    public String get_id() {
        return _id;
    }

    public List<Tag> getCategory() {
        return category;
    }

    public void setCategory(List<Tag> category) {
        this.category = category;
    }

    private enum Tag {
        CLOTHING,
        GARDENING,
        DIY,
        OUTDOORS,
        COOKERY,
        SPORTS,
        TRANSPORT,
        ELECTRONICS,
        HOME,
        FURNITURE,
        HEALTH,
        BEAUTY,
        TOYS,

    }
}
