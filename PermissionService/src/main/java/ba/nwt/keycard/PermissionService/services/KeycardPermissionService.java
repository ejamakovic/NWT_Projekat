package ba.nwt.keycard.PermissionService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ba.nwt.keycard.PermissionService.models.KeycardPermission;
import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.models.Permission;
import ba.nwt.keycard.PermissionService.repositories.KeycardPermissionRepository;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class KeycardPermissionService {

    private final KeycardPermissionRepository keycardPermissionRepository;
    private static final Logger LOGGER = Logger.getLogger(KeycardPermissionService.class.getName());

    @Autowired
    public KeycardPermissionService(KeycardPermissionRepository keycardPermissionRepository) {
        this.keycardPermissionRepository = keycardPermissionRepository;
    }

    public List<KeycardPermission> getAllKeycardPermissions() {
        LOGGER.info("Fetching all keycard permissions");
        return keycardPermissionRepository.findAll();
    }

    public List<Permission> getPermissionsByKeycardId(Integer keycardId) {
        return keycardPermissionRepository.findByKeycardId(keycardId).stream()
                .map(KeycardPermission::getPermission)
                .collect(Collectors.toList());
    }

    public List<Keycard> getKeycardsByPermissionId(Integer permissionId) {
        return keycardPermissionRepository.findByPermissionId(permissionId).stream()
                .map(KeycardPermission::getKeycard)
                .collect(Collectors.toList());
    }

    public List<Permission> getKeycardPermissionsByKeycardId(Integer keycardId) {
    }
}
