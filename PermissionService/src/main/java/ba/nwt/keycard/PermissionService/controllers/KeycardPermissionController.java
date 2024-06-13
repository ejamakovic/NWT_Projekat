package ba.nwt.keycard.PermissionService.controllers;

import ba.nwt.keycard.PermissionService.models.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ba.nwt.keycard.PermissionService.services.KeycardPermissionService;
import ba.nwt.keycard.PermissionService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.PermissionService.models.KeycardPermission;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<?>> getAllPermissionsByKeycardId(@PathVariable("keycardId") Integer keycardId) {
        List<Permission> permissions = keycardPermissionService.getPermissionsByKeycardId(keycardId);
        if (permissions.isEmpty()) {
            throw new ResourceNotFoundException("No permissions found for keycard with id: " + keycardId);
        }
        return ResponseEntity.ok().body(permissions);
    }

    @GetMapping("/checkPermission/{keycardId}")
    public ResponseEntity<Boolean> checkPermission(@PathVariable Integer keycardId, @RequestParam Integer buildingId,
            @RequestParam Integer floorId, @RequestParam Integer roomId) {
        Boolean hasPermission = keycardPermissionService.checkPermission(keycardId, buildingId, floorId, roomId);
        return ResponseEntity.ok().body(hasPermission);
    }

}
