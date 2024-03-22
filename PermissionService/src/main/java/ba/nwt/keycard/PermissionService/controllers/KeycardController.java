package ba.nwt.keycard.PermissionService.controllers;

import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.models.Permission;
import ba.nwt.keycard.PermissionService.services.KeycardService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Keycard> getKeycardById(@PathVariable("id") Integer id) {
        Keycard keycard = keycardService.getKeycardById(id);
        if (keycard != null) {
            return ResponseEntity.ok().body(keycard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/active")
    public ResponseEntity<String> updateActiveStatus(@PathVariable("id") Integer id, @RequestParam Boolean active) {
        keycardService.updateActiveStatus(id, active);
        return ResponseEntity.ok().body("Active status updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKeycard(@PathVariable("id") Integer id) {
        keycardService.deleteKeycard(id);
        return ResponseEntity.ok().body("Keycard deleted successfully");
    }

    @GetMapping("/{keycardId}/permissions")
    public ResponseEntity<List<Permission>> getKeycardPermissions(@PathVariable("keycardId") Integer keycardId) {
        List<Permission> permissions = keycardPermissionService.getPermissionsByKeycardId(keycardId);
        return ResponseEntity.ok().body(permissions);
    }
    @PostMapping
    public ResponseEntity<Keycard> createKeycard(@RequestBody Keycard keycard) {

        if (keycard == null || keycard.getIsActive() == null) {
            return ResponseEntity.badRequest().body(null); // Return a 400 Bad Request if validation fails
        }

        Keycard savedKeycard = keycardService.createKeycard(keycard);
        return ResponseEntity.ok().body(savedKeycard);
    }

}
