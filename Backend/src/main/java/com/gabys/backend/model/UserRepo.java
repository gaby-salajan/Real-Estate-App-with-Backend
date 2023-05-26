package com.gabys.backend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, String> {

    User getUserByUsername(String username);

    List<User> getUsersByRole(Integer role);
}
