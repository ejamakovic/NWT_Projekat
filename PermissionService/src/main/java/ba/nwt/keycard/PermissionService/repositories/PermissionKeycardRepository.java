package ba.nwt.keycard.PermissionService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ba.nwt.keycard.PermissionService.models.PermissionKeycard;

@Repository
public interface PermissionKeycardRepository extends JpaRepository<PermissionKeycard, Integer> {
}
