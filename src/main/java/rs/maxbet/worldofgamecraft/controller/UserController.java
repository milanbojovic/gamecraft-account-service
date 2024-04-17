package rs.maxbet.worldofgamecraft.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.maxbet.worldofgamecraft.data.transport.UserRegistrationEvent;
import rs.maxbet.worldofgamecraft.service.MessageService;
import rs.maxbet.worldofgamecraft.service.UserService;
import rs.maxbet.worldofgamecraft.data.Users;

import java.util.List;

@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    private final UserService userService;
    private final MessageService messageService;

    public UserController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping(value = "/register")
    public void createUser(@RequestBody Users user) {
        userService.createUser(user);
        messageService.publishUserRegistration(new UserRegistrationEvent(user));
    }

    @GetMapping(value = "/users")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody Users users) {
        return userService.login(users);
    }
}
