package ba.nwt.keycard.PermissionService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ba.nwt.keycard.PermissionService.models.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    // Define a method to save a Permission entity
    Permission save(Permission permission);
}
