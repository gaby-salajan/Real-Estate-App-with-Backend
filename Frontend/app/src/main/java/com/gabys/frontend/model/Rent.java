package com.gabys.frontend.model;

public class Rent {
    private int id;
    private String clientUsername;
    private int propertyId;

    public Rent(int id, String clientUsername, int propertyId) {
        this.id = id;
        this.clientUsername = clientUsername;
        this.propertyId = propertyId;
    }

    public Rent(String clientUsername, int propertyId) {
        this.clientUsername = clientUsername;
        this.propertyId = propertyId;
    }

    public Rent() {

    }

    public Rent(User client, Property property) {
        this.clientUsername = client.getUsername();
        this.propertyId = property.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }
}
