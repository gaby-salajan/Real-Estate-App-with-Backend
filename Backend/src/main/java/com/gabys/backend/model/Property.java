package com.gabys.backend.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String title;
    private String location;
    private int roomsNo;
    private int type;
    private Float price;
    private int isAvailable;
    private String imageURL;

    public Property() {}

    public Property(String title, String location, int roomsNo, int type, Float price, int isAvailable, String imageURL) {
        this.title = title;
        this.location = location;
        this.roomsNo = roomsNo;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
        this.imageURL = imageURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return roomsNo == property.roomsNo && isAvailable == property.isAvailable && title.equals(property.title) && location.equals(property.location) && type == property.type && price.equals(property.price) && Objects.equals(imageURL, property.imageURL);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRoomsNo() {
        return roomsNo;
    }

    public void setRoomsNo(int roomsNo) {
        this.roomsNo = roomsNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}