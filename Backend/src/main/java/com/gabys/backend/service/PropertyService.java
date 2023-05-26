package com.gabys.backend.service;

import com.gabys.backend.model.Property;
import com.gabys.backend.model.PropertyRepo;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepo propertyRepo;

    public PropertyService(PropertyRepo propertyRepo) {
        this.propertyRepo = propertyRepo;
    }

    public Property addProperty(Property property) {
        return propertyRepo.save(property);
    }

    public Iterable<Property> getAvailableProperties() {
        return propertyRepo.findAllByAvailable();
    }

    public Iterable<Property> getAllProperties() {
        return propertyRepo.findAll();
    }

    public Property getPropertyById(int id) {
        return propertyRepo.getReferenceById(id);
    }

    public Property updateProperty(Property property) {
        return propertyRepo.save(property);
    }

    public void deleteById(int id) {
        propertyRepo.deleteById(id);
    }
}
