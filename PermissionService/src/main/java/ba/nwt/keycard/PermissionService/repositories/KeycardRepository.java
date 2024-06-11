package ba.nwt.keycard.PermissionService.repositories;

import ba.nwt.keycard.PermissionService.models.Keycard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KeycardRepository extends JpaRepository<Keycard, Integer> {
}
