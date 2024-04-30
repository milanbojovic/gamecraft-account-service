package rs.maxbet.worldofgamecraft.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import rs.maxbet.worldofgamecraft.dao.UserRepository;
import rs.maxbet.worldofgamecraft.data.Users;
import rs.maxbet.worldofgamecraft.security.JwtUtil;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    @MockBean
    private JwtUtil jwtUtil;


    private Users user;

    @BeforeEach
    public void setUp() {
        user = new Users();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setRole("ROLE_User");
    }

    @Test
    @DisplayName("Should return token when valid user logs in")
    public void shouldReturnTokenWhenValidUserLogsIn() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        when(jwtUtil.generateToken(user.getUsername(), user.getRole(), user.getId())).thenReturn("token");

        ResponseEntity<?> response = userService.login(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{Bearer=token}", response.getBody().toString());
    }

    @Test
    @DisplayName("Should return unauthorized when invalid user logs in")
    public void shouldReturnUnauthorizedWhenInvalidUserLogsIn() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);

        ResponseEntity<?> response = userService.login(user);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("{error=Invalid username or password}", response.getBody().toString());
    }


    @Test
    @DisplayName("Should return user when user is succesfully created")
    public void testCreateUser() {
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.createUser(user));
    }

    @Test
    @DisplayName("Should return null when user is created with invalid role")
    public void shouldReturnUnauthorizedWhenUserWithInvalidRoleLogsIn() {
        user.setRole("ROLE_Invalid");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);


        assertNull(userService.createUser(user));
    }

    @Test
    @DisplayName("Should return all users")
    public void shouldReturnAllUsers() {
        List<Users> usersList = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(usersList);

        List<Users> response = userService.getAllUsers();

        assertEquals(usersList, response);
    }
}