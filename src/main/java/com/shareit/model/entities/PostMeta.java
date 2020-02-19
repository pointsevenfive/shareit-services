package com.shareit.model.entities;

import com.shareit.model.entities.audit.Record;
import org.springframework.data.annotation.Id;

public class PostMeta extends Record {

    @Id
    private String _id;

    private Long viewCount;

    public String getId() {
        return _id;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }
}
