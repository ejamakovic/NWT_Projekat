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

    public Boolean checkPermission(Integer keycardId, Integer buildingId, Integer floorId, Integer roomId) {
        List<KeycardPermission> keycardPermissions = keycardPermissionRepository.findByKeycardId(keycardId);
        for (KeycardPermission keycardPermission : keycardPermissions) {
            if (keycardPermission.getPermission().getBuildingId().equals(buildingId)
                    || keycardPermission.getPermission().getFloorId().equals(floorId)
                    || keycardPermission.getPermission().getRoomId().equals(roomId)) {
                return true;
            }
        }
        return false;
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

}
