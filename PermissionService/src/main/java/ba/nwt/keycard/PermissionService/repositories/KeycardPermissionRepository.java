package ba.nwt.keycard.PermissionService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ba.nwt.keycard.PermissionService.models.KeycardPermission;
import java.util.List;
@Repository
public interface KeycardPermissionRepository extends JpaRepository<KeycardPermission, Integer> {

    List<KeycardPermission> findByKeycardId(Integer keycardId);

    List<KeycardPermission> findByPermissionId(Integer permissionId);
}

