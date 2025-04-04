package org.example.jee_lab.entities;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(length = 40)
    private String street;

    @Column(length = 7)
    private String postalCode;

    @Column(length = 20)
    private String city;

    public Address() {
    }

    public Address(long ID, String street, String postalCode, String city) {
        this.ID = ID;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
