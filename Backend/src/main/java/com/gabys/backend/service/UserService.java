package com.gabys.backend.service;

import com.gabys.backend.model.User;
import com.gabys.backend.model.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public User addUser(User user){
        return userRepo.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public Iterable<User> getUsersByRole(int role) {
        return userRepo.getUsersByRole(role);
    }

    public Iterable<User> getAuthUsers() {
        List<User> userList = userRepo.getUsersByRole(1);
        userList.addAll(userRepo.getUsersByRole(2));
        return userList;
    }

    public User updateUser(String username, User user) {
        userRepo.deleteById(username);
        return userRepo.save(user);
    }

    public void deleteById(String username) {
        userRepo.deleteById(username);
    }
}
