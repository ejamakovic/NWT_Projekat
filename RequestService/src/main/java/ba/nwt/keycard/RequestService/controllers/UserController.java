package ba.nwt.keycard.RequestService.controllers;

import java.util.Arrays;
import java.util.List;

import ba.nwt.keycard.RequestService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.models.User.UserDTO;
import ba.nwt.keycard.RequestService.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    Environment environment;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsersWithSpecificRoles() {
        List<String> roles = Arrays.asList("user", "manager");
        List<User> users = userService.getUsersByRoles(roles);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/testFeign/{id}")
    @ResponseBody
    public ResponseEntity<String> testFeign(@PathVariable Long id) {
        String port = environment.getProperty("local.server.port");
        String response = "Response from RequestService: " + port;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
        boolean deleted = userService.deleteUserByUsername(username);
        if (deleted) {
            return ResponseEntity.ok("User with username " + username + " has been deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with username: " + username);
        }
    }

    @GetMapping("/{userId}/permissions")
    public ResponseEntity<List<?>> getPermissionsByUserId(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(userService.getPermissionsByUserId(userId), HttpStatus.OK);
    }

    @PutMapping("/{id}/team/{teamId}")
    public ResponseEntity<User> setUserTeamId(@PathVariable("id") Long userId, @PathVariable("teamId") Long teamId) {
        User updatedUser = userService.updateTeamId(userId, teamId);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getUserIdByCardId/{keycard_id}")
    public ResponseEntity<Long> getUserIdByCardId(@PathVariable("keycard_id") Long keycardId) {
        Long userId = userService.getUserIdByCardId(keycardId);
        if (userId == null) {
            throw new ResourceNotFoundException("User not found with keycard id " + keycardId);
        }
        System.out.println(userId);
        return ResponseEntity.ok(userId);
    }

    @PutMapping("/{userId}/keycard/{keycardId}")
    public ResponseEntity<User> updateKeycardId(@PathVariable("userId") Long userId,
            @PathVariable("keycardId") Long keycardId) {
        User updatedUser = userService.updateKeycard(userId, keycardId);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getKeycardIdByUserId/{userId}")
    public ResponseEntity<Long> getKeycardIdByUserId(@PathVariable("userId") Long userId) {
        Long keycardId = userService.getKeycardIdByUserId(userId);
        if (keycardId == null) {
            throw new ResourceNotFoundException("Keycard not found for user id " + userId);
        }
        return ResponseEntity.ok(keycardId);
    }

}
