package com.gabys.backend.controller;

import com.gabys.backend.model.User;
import com.gabys.backend.service.EmailService;
import com.gabys.backend.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/user")
public class UserController {

    private final EmailService emailService;
    private final UserService userService;

    public UserController(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @PostMapping(path="/add")
    public @ResponseBody User addNewUser (@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping(path="/find")
    public @ResponseBody User getUser(@RequestParam String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path="/clients")
    public @ResponseBody Iterable<User> getAllClients() {
        return userService.getUsersByRole(0);
    }


    @GetMapping(path="/auth_users")
    public @ResponseBody Iterable<User> getAuthUsers() {
        return userService.getAuthUsers();
    }

    @PutMapping(path="/update")
    public @ResponseBody User updateUser(@RequestParam String username, @RequestBody User user) {
        return userService.updateUser(username, user);
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody void deleteUser(@RequestParam String username) {
        userService.deleteById(username);
    }

    @PutMapping(path="/send_email")
    public @ResponseBody void notifyUser(@RequestBody User user) {
        emailService.sendEmail(user);
    }
}
