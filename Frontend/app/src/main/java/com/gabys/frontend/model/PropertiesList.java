package com.gabys.frontend.model;

import java.util.ArrayList;

public class PropertiesList {
    ArrayList<Property> propertiesList;

    public PropertiesList() {
        propertiesList = new ArrayList<>();
    }

    public PropertiesList(ArrayList<Property> propertiesList) {
        this.propertiesList = propertiesList;
    }

    public ArrayList<Property> getPropertiesList() {
        return propertiesList;
    }

    public void setPropertiesList(ArrayList<Property> propertiesList) {
        this.propertiesList = propertiesList;
    }
}
