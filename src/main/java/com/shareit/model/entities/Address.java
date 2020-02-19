package com.shareit.model.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.shareit.model.entities.audit.Record;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address extends Record {

    @NotBlank
    private String firstLine;

    private String secondLine;

    private String thirdLine;

    @NotBlank
    private String city;

    @NotBlank
    @Pattern(regexp = "[A-Z]{1,2}[0-9]{1,2}\\s?[0-9]{1}[A-Z]{2}")
    private String postCode;

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine;
    }

    public String getThirdLine() {
        return thirdLine;
    }

    public void setThirdLine(String thirdLine) {
        this.thirdLine = thirdLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
