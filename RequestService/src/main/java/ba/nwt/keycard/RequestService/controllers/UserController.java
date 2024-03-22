package ba.nwt.keycard.RequestService.controllers;

import java.util.List;

import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.models.User.UserDTO;
import ba.nwt.keycard.RequestService.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping( "/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
    }
}
