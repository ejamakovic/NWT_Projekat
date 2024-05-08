package ba.nwt.keycard.RequestService.controllers;

import ba.nwt.keycard.RequestService.models.Keycard;
import ba.nwt.keycard.RequestService.services.KeycardService;
import ba.nwt.keycard.RequestService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user/keycard")
public class KeycardController {

    @Autowired
    private KeycardService keycardService;

    @GetMapping
    public ResponseEntity<List<Keycard>> getAllKeycards(){
        return new ResponseEntity<>(keycardService.getAllKeycards(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Keycard> getKeycardById(@PathVariable Long id){
        return new ResponseEntity<>(keycardService.getKeycardById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Keycard> createKeycard(@Valid @RequestBody Keycard keycard){
        return new ResponseEntity<>(keycardService.createKeycard(keycard), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKeycardById(@PathVariable Long id) {
        boolean deleted = keycardService.deleteKeycardById(id);
        if (deleted) {
            return ResponseEntity.ok("Keycard with id " + id + " has been deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Keycard not found with id: " + id);
        }
    }

}

