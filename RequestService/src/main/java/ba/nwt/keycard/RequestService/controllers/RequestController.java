package ba.nwt.keycard.RequestService.controllers;

import ba.nwt.keycard.RequestService.models.Request.Request;
import ba.nwt.keycard.RequestService.models.Request.RequestDTO;
import ba.nwt.keycard.RequestService.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests(){
        return new ResponseEntity<>(requestService.getAllRequests(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable("id") Long id){
        return new ResponseEntity<>(requestService.getRequestById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Request> createRequest(@Valid @RequestBody RequestDTO requestDTO){
        return new ResponseEntity<>(requestService.createRequest(requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequestById(@PathVariable("id") Long id) {
        boolean deleted = requestService.deleteRequestById(id);
        if (deleted) {
            return ResponseEntity.ok("Request with id " + id + " has been deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request not found with id: " + id);
        }
    }

    @GetMapping("/rooms/{userId}")
    public ResponseEntity<List<?>> getAllRooms(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(requestService.getAllRoomsForUser(userId), HttpStatus.OK);
    }
}
