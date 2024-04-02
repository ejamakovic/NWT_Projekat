package ba.nwt.keycard.RoomService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ba.nwt.keycard.RoomService.models.Floor.Floor;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    List<Floor> findFloorsByBuildingId(Long buildingId);
}
