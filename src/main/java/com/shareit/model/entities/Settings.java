package com.shareit.model.entities;

import com.shareit.model.entities.audit.Record;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Settings extends Record {

    @Id
    private String _id;

    private Boolean notifications;

    public String get_id() {
        return _id;
    }

    public Boolean getNotifications() {
        return notifications;
    }

    public void setNotifications(Boolean notifications) {
        this.notifications = notifications;
    }
}
