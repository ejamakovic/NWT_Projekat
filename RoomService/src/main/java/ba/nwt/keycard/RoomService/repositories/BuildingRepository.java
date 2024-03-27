package ba.nwt.keycard.RoomService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ba.nwt.keycard.RoomService.models.Building.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
}
