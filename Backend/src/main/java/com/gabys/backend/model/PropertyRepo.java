package com.gabys.backend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PropertyRepo extends JpaRepository<Property, Integer> {

    @Query("select p from Property p where p.isAvailable = 1")
    List<Property> findAllByAvailable();
}
