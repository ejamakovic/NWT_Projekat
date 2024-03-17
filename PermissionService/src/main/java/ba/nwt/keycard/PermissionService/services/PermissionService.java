package ba.nwt.keycard.PermissionService.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.security.Permission;

public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    // Dodavanje nekoliko unosa u tabelu "permissions"
    Permission permission1 = new Permission();
        permission1.setRole(Role.ADMIN);
        permission1.setRoomId(1);
        permission1.setFloorId(1);
        permission1.setBuildingId(1);
        permissionRepository.save(permission1);

    Permission permission2 = new Permission();
        permission2.setRole(Role.USER);
        permission2.setRoomId(2);
        permission2.setFloorId(2);
        permission2.setBuildingId(2);
        permissionRepository.save(permission2);
}
