package ba.nwt.keycard.PermissionService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ba.nwt.keycard.PermissionService.models.KeycardPermission;
import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.models.Permission;
import java.util.List;
@Repository
public interface KeycardPermissionRepository extends JpaRepository<KeycardPermission, Integer> {
    List<Permission> findPermissionsByKeycardId(Integer keycardId);
    List<Keycard> findKeycardsByPermissionId(Integer permisssionId);
}

