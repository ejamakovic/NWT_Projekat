package ba.nwt.keycard.RequestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import ba.nwt.keycard.RequestService.controllers.UserController;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.models.User.UserDTO;
import ba.nwt.keycard.RequestService.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userServiceMock;

    @BeforeEach
    public void setUp() {
        userServiceMock = mock(UserService.class);
        userController = new UserController();
        userController.setUserService(userServiceMock);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        when(userServiceMock.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> responseEntity = userController.getAllUsers();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(users, responseEntity.getBody());
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userServiceMock.getUserById(userId)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.getUserById(userId);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    public void testGetUserByUsername() {
        String username = "testuser";
        User user = new User();
        user.setUsername(username);

        when(userServiceMock.getUserByUsername(username)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.getUserByUsername(username);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");

        User user = new User();
        user.setUsername(userDTO.getUsername());

        when(userServiceMock.createUser(userDTO)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.createUser(userDTO);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    public void testDeleteUserByUsername() {
        String username = "testuser";

        when(userServiceMock.deleteUserByUsername(username)).thenReturn(true);

        ResponseEntity<String> responseEntity = userController.deleteUserByUsername(username);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User with username " + username + " has been deleted successfully.", responseEntity.getBody());
    }
}

