package ba.nwt.keycard.PermissionService.controllers;

import ba.nwt.keycard.PermissionService.models.Permission;
import ba.nwt.keycard.PermissionService.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionController(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    // Get all permissions
    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return ResponseEntity.ok().body(permissions);
    }

    // Get permission by ID
    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Integer id) {
        return permissionRepository.findById(id)
                .map(permission -> ResponseEntity.ok().body(permission))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get permissions by room ID
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Permission>> getPermissionsByRoomId(@PathVariable Integer roomId) {
        List<Permission> permissions = permissionRepository.findByRoomId(roomId);
        return ResponseEntity.ok().body(permissions);
    }

    // Get permissions by floor ID
    @GetMapping("/floor/{floorId}")
    public ResponseEntity<List<Permission>> getPermissionsByFloorId(@PathVariable Integer floorId) {
        List<Permission> permissions = permissionRepository.findByFloorId(floorId);
        return ResponseEntity.ok().body(permissions);
    }

    // Get permissions by building ID
    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<Permission>> getPermissionsByBuildingId(@PathVariable Integer buildingId) {
        List<Permission> permissions = permissionRepository.findByBuildingId(buildingId);
        return ResponseEntity.ok().body(permissions);
    }

    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        Permission createdPermission = permissionRepository.save(permission);
        return ResponseEntity.ok().body(createdPermission);
    }
}
