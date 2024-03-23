package ba.nwt.keycard.RequestService.controllers;

import ba.nwt.keycard.RequestService.models.Request;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.services.RequestService;
import ba.nwt.keycard.RequestService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<Request>> getAllRequests(){
        return new ResponseEntity<>(requestService.getAllRequests(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Request> getRequestById(@PathVariable Long id){
        return new ResponseEntity<>(requestService.getRequestById(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Request> createRequest(@Valid @RequestBody Request request, @PathVariable Long id){
        User user = userService.getUserById(id);
        request.setUser(user);
        request.setTeam(user.getTeam());
        return new ResponseEntity<>(requestService.createRequest(request), HttpStatus.OK);
    }
}
