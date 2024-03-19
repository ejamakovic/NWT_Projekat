package ba.nwt.keycard.PermissionService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ba.nwt.keycard.PermissionService.models.KeycardPermission;
import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.models.Permission;
import ba.nwt.keycard.PermissionService.repositories.KeycardPermissionRepository;
import java.util.List;

@Service
public class KeycardPermissionService {

    private final KeycardPermissionRepository keycardPermissionRepository;

    @Autowired
    public KeycardPermissionService(KeycardPermissionRepository keycardPermissionRepository) {
        this.keycardPermissionRepository = keycardPermissionRepository;
    }

    public List<KeycardPermission> getAllKeycardPermissions() {
        return keycardPermissionRepository.findAll();
    }

    public List<Permission> getPermissionsForKeycard(Integer keycardId) {
        return keycardPermissionRepository.findPermissionsByKeycardId(keycardId);
    }

    public List<Keycard> getKeycardsForPermission(Integer permissionId) {
        return keycardPermissionRepository.findKeycardsByPermissionId(permissionId);
    }
}
