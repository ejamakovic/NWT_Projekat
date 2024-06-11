package ba.nwt.keycard.RoomService.repositories;

import ba.nwt.keycard.RoomService.models.Floor.Floor;
import ba.nwt.keycard.RoomService.models.Room.FullRoomDTO;
import ba.nwt.keycard.RoomService.models.Room.Room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findRoomsByFloorId(Long floorId);

    @Query("SELECT B.id FROM Room R JOIN R.floor F JOIN F.building B WHERE R.id = :roomId")
    Long findBuildingIdByRoomId(Long roomId);

    @Query("SELECT F.id FROM Room R JOIN R.floor F WHERE R.id = :roomId")
    Long findFloorIdByRoomId(Long roomId);

    @Query("SELECT r FROM Room r JOIN r.floor f JOIN f.building b WHERE b.id = :buildingId ORDER BY f.id")
    List<Room> findRoomsByBuildingIdSortedByFloorId(Long buildingId);
}
