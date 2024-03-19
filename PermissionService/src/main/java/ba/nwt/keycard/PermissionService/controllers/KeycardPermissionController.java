package ba.nwt.keycard.PermissionService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ba.nwt.keycard.PermissionService.services.KeycardPermissionService;
import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.models.Permission;
import ba.nwt.keycard.PermissionService.models.KeycardPermission;

import java.util.List;

@RestController
@RequestMapping("/api/keycardpermissions")
public class KeycardPermissionController {

    private final KeycardPermissionService keycardPermissionService;

    @Autowired
    public KeycardPermissionController(KeycardPermissionService keycardPermissionService) {
        this.keycardPermissionService = keycardPermissionService;
    }

    @GetMapping
    public ResponseEntity<List<KeycardPermission>> getAllKeycardPermissions() {
        List<KeycardPermission> keycardPermissions = keycardPermissionService.getAllKeycardPermissions();
        return ResponseEntity.ok().body(keycardPermissions);
    }

    @GetMapping("/keycard/{keycardId}")
    public ResponseEntity<List<Permission>> getPermissionsForKeycard(@PathVariable("keycardId") Integer keycardId) {
        List<Permission> permissions = keycardPermissionService.getPermissionsForKeycard(keycardId);
        return ResponseEntity.ok().body(permissions);
    }

    @GetMapping("/permission/{permissionId}")
    public ResponseEntity<List<Keycard>> getKeycardsForPermission(@PathVariable("permissionId") Integer permissionId) {
        List<Keycard> keycards = keycardPermissionService.getKeycardsForPermission(permissionId);
        return ResponseEntity.ok().body(keycards);
    }
}
