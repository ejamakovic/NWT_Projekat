package ba.nwt.keycard.PermissionService.controllers;

import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.repositories.KeycardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/keycards")
public class KeycardController {

    private final KeycardRepository keycardRepository;

    @Autowired
    public KeycardController(KeycardRepository keycardRepository) {
        this.keycardRepository = keycardRepository;
    }

    @GetMapping
    public ResponseEntity<List<Keycard>> getAllKeycards() {
        List<Keycard> keycards = keycardRepository.findAll();
        return ResponseEntity.ok().body(keycards);
    }
}
