package ba.nwt.keycard.PermissionService.services;

import ba.nwt.keycard.PermissionService.models.Permission;
import ba.nwt.keycard.PermissionService.repositories.PermissionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Permission getPermissionById(Integer id) {
        return permissionRepository.findById(id).orElse(null);
    }

    public List<Permission> getPermissionsByRoomId(Integer roomId) {
        return permissionRepository.findByRoomId(roomId);
    }

    public List<Permission> getPermissionsByFloorId(Integer floorId) {
        return permissionRepository.findByFloorId(floorId);
    }

    public List<Permission> getPermissionsByBuildingId(Integer buildingId) {
        return permissionRepository.findByBuildingId(buildingId);
    }

    @Transactional
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }
}
