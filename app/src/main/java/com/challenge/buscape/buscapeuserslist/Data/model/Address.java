package com.challenge.buscape.buscapeuserslist.data.model;

import java.io.Serializable;

/**
 * Created by eliete on 7/25/16.
 */
public class Address implements Serializable {

    String street;
    String suite;
    String city;
    String zipcode;


    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }


    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
