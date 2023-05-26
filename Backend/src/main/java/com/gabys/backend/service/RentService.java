package com.gabys.backend.service;

import com.gabys.backend.model.Rent;
import com.gabys.backend.model.RentRepo;
import org.springframework.stereotype.Service;

@Service
public class RentService {

    private final RentRepo rentRepo;

    public RentService(RentRepo rentRepo) {
        this.rentRepo = rentRepo;
    }

    public Iterable<Rent> findAll() {
        return rentRepo.findAll();
    }

    public Rent getRentById(int id) {
        return rentRepo.getReferenceById(id);
    }

    public Rent addRent(Rent rent) {
        return rentRepo.save(rent);
    }

    public void deleteById(int id) {
        rentRepo.deleteById(id);
    }
}
