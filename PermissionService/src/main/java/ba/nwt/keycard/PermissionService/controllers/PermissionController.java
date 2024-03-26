package ba.nwt.keycard.PermissionService.controllers;

import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.models.Permission;
import ba.nwt.keycard.PermissionService.services.KeycardPermissionService;
import ba.nwt.keycard.PermissionService.services.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getPermissionById(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ID is required");
        }

        if (id <= 0) {
            return ResponseEntity.badRequest().body("ID must be a positive number");
        }

        Permission permission = permissionService.getPermissionById(id);
        if (permission != null) {
            return ResponseEntity.ok().body(permission);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get permissions by room ID
    @GetMapping("/room/{roomId}")
    public ResponseEntity<?> getPermissionsByRoomId(@PathVariable Integer roomId) {
        if (roomId == null) {
            return ResponseEntity.badRequest().body("Room ID is required");
        }

        if (roomId <= 0) {
            return ResponseEntity.badRequest().body("Room ID must be a positive number");
        }
        List<Permission> permissions = permissionService.getPermissionsByRoomId(roomId);
        return ResponseEntity.ok().body(permissions);
    }

    // Get permissions by floor ID
    @GetMapping("/floor/{floorId}")
    public ResponseEntity<?> getPermissionsByFloorId(@PathVariable Integer floorId) {
        if (floorId == null) {
            return ResponseEntity.badRequest().body("Room ID is required");
        }

        if (floorId <= 0) {
            return ResponseEntity.badRequest().body("Room ID must be a positive number");
        }

        List<Permission> permissions = permissionService.getPermissionsByFloorId(floorId);
        return ResponseEntity.ok().body(permissions);
    }

    // Get permissions by building ID
    @GetMapping("/building/{buildingId}")
    public ResponseEntity<?> getPermissionsByBuildingId(@PathVariable Integer buildingId) {
        if (buildingId == null) {
            return ResponseEntity.badRequest().body("Room ID is required");
        }

        if (buildingId <= 0) {
            return ResponseEntity.badRequest().body("Room ID must be a positive number");
        }
        List<Permission> permissions = permissionService.getPermissionsByBuildingId(buildingId);
        return ResponseEntity.ok().body(permissions);
    }

    @PostMapping
    public ResponseEntity<?> createPermission(@Valid @RequestBody Permission permission) {
        if (permission == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (permission.getRole() == null) {
            return ResponseEntity.badRequest().body("Role is required");
        }

        if (permission.getRoomId() == null) {
            return ResponseEntity.badRequest().body("Room ID is required");
        }

        Permission createdPermission = permissionService.createPermission(permission);
        return ResponseEntity.ok().body(createdPermission);
    }

    @GetMapping("/{permissionId}/keycards")
    public ResponseEntity<?> getKeycardsByPermissionId(@PathVariable("permissionId") Integer permissionId) {
        if (permissionId == null) {
            return ResponseEntity.badRequest().body("Room ID is required");
        }

        if (permissionId <= 0) {
            return ResponseEntity.badRequest().body("Room ID must be a positive number");
        }

        List<Keycard> keycards = keycardPermissionService.getKeycardsByPermissionId(permissionId);
        return ResponseEntity.ok().body(keycards);
    }
}
