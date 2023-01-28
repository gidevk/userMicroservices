package com.user.entity;

import java.util.ArrayList;
import java.util.List;

public class userEntity {

    private Long UserId;
    private String name;
    private String phone;
    List<Contact> contact = new ArrayList<>();


    public userEntity(Long userId, String name, String phone) {
        UserId = userId;
        this.name = name;
        this.phone = phone;
    }

    public userEntity(Long userId, String name, String phone, List<Contact> contact) {
        UserId = userId;
        this.name = name;
        this.phone = phone;
        this.contact = contact;
    }
    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }
}
