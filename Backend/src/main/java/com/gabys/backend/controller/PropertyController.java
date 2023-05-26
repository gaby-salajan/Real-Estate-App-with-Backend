package com.gabys.backend.controller;

import com.gabys.backend.model.Property;
import com.gabys.backend.service.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping(path="/add")
    public @ResponseBody Property addNewProperty (@RequestParam String title,
                                                @RequestParam String location,
                                                @RequestParam int roomsNo,
                                                @RequestParam int type,
                                                @RequestParam Float price,
                                                @RequestParam int isAvailable,
                                                @RequestParam String imageURL) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Property property = new Property(title, location, roomsNo, type, price, isAvailable, imageURL);
        return propertyService.addProperty(property);
    }

    @GetMapping(path="/available")
    public @ResponseBody Iterable<Property> getAvailableProperties(){
        return propertyService.getAvailableProperties();
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping(path="/find")
    public @ResponseBody Property getPropertyById(@RequestParam int id) {
        return propertyService.getPropertyById(id);
    }

    @PutMapping(path="/update")
    public @ResponseBody Property updateProperty(@RequestBody Property property) {
        return propertyService.updateProperty(property);
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody void deleteProperty(@RequestParam int id) {
        propertyService.deleteById(id);
    }

}
