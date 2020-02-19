package com.shareit.model.entities;

import com.shareit.model.entities.audit.Record;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PaymentInfo extends Record {

    private String _id;

    private String cardNumber;

    private Address address;

    public String get_id() {
        return _id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
