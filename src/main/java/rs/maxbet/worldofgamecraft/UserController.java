package rs.maxbet.worldofgamecraft;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.maxbet.worldofgamecraft.data.Users;
import rs.maxbet.worldofgamecraft.security.JwtUtil;

import java.util.List;

@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    private final UserService userService;
    private final RabbitTemplate rabbitTemplate;

    public UserController(UserService userService, RabbitTemplate rabbitTemplate) {
        this.userService = userService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping(value = "/register")
    public void createUser(@RequestBody Users user) {
        userService.createUser(user);
        //rabbitTemplate.convertAndSend("", "q.user-registration", user.getId()); //TODO
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
