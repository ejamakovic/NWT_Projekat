package ba.nwt.keycard.PermissionService.repositories;

import ba.nwt.keycard.PermissionService.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    List<Permission> findByRoomId(Integer roomId);
    List<Permission> findByFloorId(Integer floorId);
    List<Permission> findByBuildingId(Integer buildingId);
}
