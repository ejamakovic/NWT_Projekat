package ba.nwt.keycard.RoomService.repositories;

import ba.nwt.keycard.RoomService.models.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer> {
}
