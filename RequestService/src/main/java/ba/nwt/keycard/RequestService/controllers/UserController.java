package ba.nwt.keycard.RequestService.controllers;

import java.util.List;

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

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
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
    public ResponseEntity<List<String>> getUserPermissions(@PathVariable("userId") Long userId) {
        Long keycardId = userService.getKeycardByUserId(userId);
        List<String> permissions = userService.getUserPermissions(keycardId);

        return ResponseEntity.ok(permissions);
    }

}
