package com.challenge.buscape.buscapeuserslist.data.model;

import java.io.Serializable;

/**
 * Created by eliete on 7/25/16.
 */
public class User implements Serializable {

    int id;
    String name;
    String email;
    Address address;
    String phone;
    String website;

    public User(int id, String name, String email, Address address, String phone, String website) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
