package ba.nwt.keycard.PermissionService.controllers;

import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.repositories.KeycardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Keycard> getKeycardById(@PathVariable("id") Integer id) {
        Optional<Keycard> keycardOptional = keycardRepository.findById(id);
        if (keycardOptional.isPresent()) {
            return ResponseEntity.ok().body(keycardOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/active")
    public ResponseEntity<String> updateActiveStatus(@PathVariable("id") Integer id, @RequestParam Boolean active) {
        Optional<Keycard> keycardOptional = keycardRepository.findById(id);
        if (keycardOptional.isPresent()) {
            Keycard keycard = keycardOptional.get();
            keycard.setActive(active);
            keycardRepository.save(keycard);
            return ResponseEntity.ok().body("Active status updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKeycard(@PathVariable("id") Integer id) {
        Optional<Keycard> keycardOptional = keycardRepository.findById(id);
        if (keycardOptional.isPresent()) {
            keycardRepository.deleteById(id);
            return ResponseEntity.ok().body("Keycard deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Keycard> createKeycard(@RequestBody Keycard keycard) {
        Keycard createdKeycard = keycardRepository.save(keycard);
        return ResponseEntity.ok().body(createdKeycard);
    }
}
