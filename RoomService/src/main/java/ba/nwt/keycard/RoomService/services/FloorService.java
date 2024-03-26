package ba.nwt.keycard.RoomService.services;

import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.models.Floor;
import ba.nwt.keycard.RoomService.repositories.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorService {

    private final FloorRepository floorRepository;

    @Autowired
    public FloorService(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }

    public Floor getFloorById(Long id) {
        return floorRepository.findById(id).orElse(null);
    }

    public List<Floor> getAllFloorsByBuildingId(Long buildingId) {
        return floorRepository.findFloorsByBuildingId(buildingId);
    }

    public void updateFloorNumber(Long id, Integer floorNumber) {
        Floor floor = floorRepository.findById(id).orElse(null);
        if (floor != null) {
            floor.setFloorNumber(floorNumber);
            floorRepository.save(floor);
        } else {
            throw new ResourceNotFoundException("Floor not found with id " + id);
        }
    }

    public void deleteFloor(Long id) {
        floorRepository.deleteById(id);
    }

    public Floor addFloor(Floor floor) {
        return floorRepository.save(floor);
    }
}
