package ba.nwt.keycard.RoomService.services;

import ba.nwt.keycard.RoomService.models.Building.Building;
import ba.nwt.keycard.RoomService.models.Floor.Floor;
import ba.nwt.keycard.RoomService.models.Room.Room;
import ba.nwt.keycard.RoomService.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitalizeService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private RoomRepository roomRepository;

    public void saveBuilding(Building building) {
        buildingRepository.save(building);
    }

    public void saveFloor(Floor floor) {
        floorRepository.save(floor);
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

}
