package com.gabys.frontend.model;

import java.util.Objects;

public class Property {

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

    public Property(int id, String title, String location, int rooms, int type, Float price, int b, String imageURL) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.roomsNo = rooms;
        this.type = type;
        this.price = price;
        this.isAvailable = b;
        this.imageURL = imageURL;
    }

    public Property(String title, String location, int rooms, int type, float price, int isAvailable) {
        this.title = title;
        this.location = location;
        this.roomsNo = rooms;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
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

    public String getImageURL() {
        return this.imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getRoomsNo() {
        return roomsNo;
    }

    public void setRoomsNo(int roomsNo) {
        this.roomsNo = roomsNo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Boolean isAvailable() {
        return isAvailable == 1;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available ? 1 : 0;
    }

    public void update(Property property) {
        this.title = property.title;
        this.location = property.location;
        this.roomsNo = property.roomsNo;
        this.type = property.type;
        this.price = property.price;
        this.isAvailable = property.isAvailable;
        this.imageURL = property.imageURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return roomsNo == property.roomsNo && isAvailable == property.isAvailable && title.equals(property.title) && location.equals(property.location) && type == property.type && price.equals(property.price) && Objects.equals(imageURL, property.imageURL);
    }

}
