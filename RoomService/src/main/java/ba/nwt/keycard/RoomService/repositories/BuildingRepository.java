package ba.nwt.keycard.RoomService.repositories;

import ba.nwt.keycard.RoomService.models.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
}
