package ba.nwt.keycard.PermissionService.services;

import ba.nwt.keycard.PermissionService.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ba.nwt.keycard.PermissionService.models.Permission;
import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.models.PermissionKeycard;
import ba.nwt.keycard.PermissionService.models.Role;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public void initializePermissions() {
        // Dodavanje nekoliko unosa u tabelu "permissions"
        ba.nwt.keycard.PermissionService.models.Permission permission1 = new ba.nwt.keycard.PermissionService.models.Permission();
        //permission1.setRole(Role.ADMIN);
        permission1.setRoomId(1);
        permission1.setFloorId(1);
        permission1.setBuildingId(1);
        permissionRepository.save(permission1);

        ba.nwt.keycard.PermissionService.models.Permission permission2 = new ba.nwt.keycard.PermissionService.models.Permission();
        //permission2.setRole(Role.USER);
        permission2.setRoomId(2);
        permission2.setFloorId(2);
        permission2.setBuildingId(2);
        permissionRepository.save(permission2);
    }
}
