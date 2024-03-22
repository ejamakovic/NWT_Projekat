package ba.nwt.keycard.PermissionService.controllers;

import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.models.Permission;
import ba.nwt.keycard.PermissionService.services.KeycardPermissionService;
import ba.nwt.keycard.PermissionService.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;
    private final KeycardPermissionService keycardPermissionService;

    @Autowired
    public PermissionController(PermissionService permissionService, KeycardPermissionService keycardPermissionService) {
        this.permissionService = permissionService;
        this.keycardPermissionService = keycardPermissionService;
    }

    // Get all permissions
    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok().body(permissions);
    }

    // Get permission by ID
    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Integer id) {
        Permission permission = permissionService.getPermissionById(id);
        if (permission != null) {
            return ResponseEntity.ok().body(permission);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get permissions by room ID
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Permission>> getPermissionsByRoomId(@PathVariable Integer roomId) {
        List<Permission> permissions = permissionService.getPermissionsByRoomId(roomId);
        return ResponseEntity.ok().body(permissions);
    }

    // Get permissions by floor ID
    @GetMapping("/floor/{floorId}")
    public ResponseEntity<List<Permission>> getPermissionsByFloorId(@PathVariable Integer floorId) {
        List<Permission> permissions = permissionService.getPermissionsByFloorId(floorId);
        return ResponseEntity.ok().body(permissions);
    }

    // Get permissions by building ID
    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<Permission>> getPermissionsByBuildingId(@PathVariable Integer buildingId) {
        List<Permission> permissions = permissionService.getPermissionsByBuildingId(buildingId);
        return ResponseEntity.ok().body(permissions);
    }

    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        Permission createdPermission = permissionService.createPermission(permission);
        return ResponseEntity.ok().body(createdPermission);
    }

    @GetMapping("/{permissionId}/keycards")
    public ResponseEntity<List<Keycard>> getKeycardsByPermissionId(@PathVariable("permissionId") Integer permissionId) {
        List<Keycard> keycards = keycardPermissionService.getKeycardsByPermissionId(permissionId);
        return ResponseEntity.ok().body(keycards);
    }
}
