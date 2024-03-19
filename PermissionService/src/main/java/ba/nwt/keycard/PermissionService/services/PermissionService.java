package ba.nwt.keycard.PermissionService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ba.nwt.keycard.PermissionService.models.Permission;
import ba.nwt.keycard.PermissionService.models.Role;
import ba.nwt.keycard.PermissionService.repositories.PermissionRepository;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public void initializePermissions() {
        // Create and save Permission entities
        Permission permission1 = new Permission();
        permission1.setRole(Role.ADMIN); // Set the Role
        permission1.setRoomId(1);
        permission1.setFloorId(1);
        permission1.setBuildingId(1);
        permissionRepository.save(permission1);

        Permission permission2 = new Permission();
        permission2.setRole(Role.USER); // Set the Role
        permission2.setRoomId(2);
        permission2.setFloorId(2);
        permission2.setBuildingId(2);
        permissionRepository.save(permission2);
    }
}
