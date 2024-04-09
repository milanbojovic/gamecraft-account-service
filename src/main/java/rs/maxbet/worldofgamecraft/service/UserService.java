package rs.maxbet.worldofgamecraft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.dao.UserRepository;
import rs.maxbet.worldofgamecraft.data.Users;
import rs.maxbet.worldofgamecraft.security.JwtUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(Users users) {
        userRepository.save(users);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<?> login(Users user) {
        Users dbUser = userRepository.findByUsername(user.getUsername());
        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("Bearer", jwtUtil.generateToken(dbUser.getUsername(), dbUser.getRole(), dbUser.getId()));
            return ResponseEntity.ok(response.toString());
        }
        Map<String, String> response = new HashMap<>();
        response.put("error", "Invalid username or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
