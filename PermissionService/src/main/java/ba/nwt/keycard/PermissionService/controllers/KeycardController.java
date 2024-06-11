package ba.nwt.keycard.PermissionService.controllers;

import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.models.Permission;
import ba.nwt.keycard.PermissionService.services.KeycardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ba.nwt.keycard.PermissionService.services.KeycardPermissionService;
import java.util.List;

@RestController
@RequestMapping("/api/keycards")
public class KeycardController {

    private final KeycardService keycardService;
    private final KeycardPermissionService keycardPermissionService;

    @Autowired
    public KeycardController(KeycardService keycardService, KeycardPermissionService keycardPermissionService) {
        this.keycardService = keycardService;
        this.keycardPermissionService = keycardPermissionService;
    }

    @GetMapping
    public ResponseEntity<List<Keycard>> getAllKeycards() {
        List<Keycard> keycards = keycardService.getAllKeycards();
        return ResponseEntity.ok().body(keycards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getKeycardById(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ID is required");
        }

        if (id <= 0) {
            return ResponseEntity.badRequest().body("ID must be a positive number");
        }

        Keycard keycard = keycardService.getKeycardById(id);
        if (keycard != null) {
            return ResponseEntity.ok().body(keycard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/active")
    public ResponseEntity<String> updateActiveStatus(@PathVariable("id") Integer id, @RequestParam Boolean active) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ID is required");
        }

        if (id <= 0) {
            return ResponseEntity.badRequest().body("ID must be a positive number");
        }

        keycardService.updateActiveStatus(id, active);
        return ResponseEntity.ok().body("Active status updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKeycard(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ID is required");
        }

        if (id <= 0) {
            return ResponseEntity.badRequest().body("ID must be a positive number");
        }

        keycardService.deleteKeycard(id);
        return ResponseEntity.ok().body("Keycard deleted successfully");
    }

    @GetMapping("/{keycardId}/permissions")
    public ResponseEntity<?> getKeycardPermissions(@PathVariable("keycardId") Integer keycardId) {
        if (keycardId == null) {
            return ResponseEntity.badRequest().body("Keycard ID is required");
        }

        if (keycardId <= 0) {
            return ResponseEntity.badRequest().body("Keycard ID must be a positive number");
        }

        List<Permission> permissions = keycardPermissionService.getPermissionsByKeycardId(keycardId);
        return ResponseEntity.ok().body(permissions);
    }

    @PostMapping
    public ResponseEntity<?> createKeycard(@Valid @RequestBody(required = false) Keycard keycard) {
        if (keycard == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body is missing");
        }

        if (keycard.getIsActive() == null) {
            return ResponseEntity.badRequest().body("Active status is required");
        }

        Keycard savedKeycard = keycardService.createKeycard(keycard);
        return ResponseEntity.ok().body(savedKeycard);
    }
}
