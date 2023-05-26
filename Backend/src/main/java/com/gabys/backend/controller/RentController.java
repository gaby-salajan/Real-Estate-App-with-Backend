package com.gabys.backend.controller;

import com.gabys.backend.model.Rent;
import com.gabys.backend.service.RentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/rent")
public class RentController {

    private RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Rent> getAllRents() {
        return rentService.findAll();
    }

    @GetMapping(path="/find")
    public @ResponseBody Rent getRentById(@RequestParam int id) {
        return rentService.getRentById(id);
    }

    @PostMapping(path = "/add")
    public @ResponseBody Rent addRent(@RequestParam String clientUsername, @RequestParam int propertyId){
        Rent rent = new Rent(clientUsername, propertyId);
        return rentService.addRent(rent);
    }

    @DeleteMapping(path = "delete")
    public @ResponseBody void deleteRent(@RequestParam int id){
        rentService.deleteById(id);
    }


}
