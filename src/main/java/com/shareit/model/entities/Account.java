package com.shareit.model.entities;

import com.shareit.model.entities.audit.Record;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account extends Record {

    private Address address;

    private PaymentInfo payment;

    private Settings settings;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PaymentInfo getPayment() {
        return payment;
    }

    public void setPayment(PaymentInfo payment) {
        this.payment = payment;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
