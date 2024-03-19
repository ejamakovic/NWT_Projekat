package ba.nwt.keycard.RoomService.services;

import ba.nwt.keycard.RoomService.models.*;
import ba.nwt.keycard.RoomService.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitalizeService {

    @Autowired
    private BuildingRepostory buildingRepostory;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private RoomRepository roomRepository;

    public void saveBuilding(Building building) {
        buildingRepostory.save(building);
    }
    public void saveFloor(Floor floor){
        floorRepository.save(floor);
    }
    public void saveRoom(Room room){
        roomRepository.save(room);
    }

}