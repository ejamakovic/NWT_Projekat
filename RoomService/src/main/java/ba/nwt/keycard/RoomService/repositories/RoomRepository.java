package ba.nwt.keycard.RoomService.repositories;

import ba.nwt.keycard.RoomService.models.Floor;
import ba.nwt.keycard.RoomService.models.Room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findRoomsByFloorId(Long floorId);

    @Query("SELECT r FROM Room r JOIN r.floor f JOIN f.building b WHERE b.id = :buildingId ORDER BY f.id")
    List<Room> findRoomsByBuildingIdSortedByFloorId(Long buildingId);
}
