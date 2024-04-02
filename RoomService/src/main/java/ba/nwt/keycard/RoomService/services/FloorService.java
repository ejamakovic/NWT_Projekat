package ba.nwt.keycard.RoomService.services;

import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.models.Floor.Floor;
import ba.nwt.keycard.RoomService.models.Floor.FloorDTO;
import ba.nwt.keycard.RoomService.models.Floor.FloorMapper;
import ba.nwt.keycard.RoomService.repositories.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorService {

    private final FloorRepository floorRepository;
    private final FloorMapper floorMapper;

    @Autowired
    public FloorService(FloorRepository floorRepository, FloorMapper floorMapper) {
        this.floorRepository = floorRepository;
        this.floorMapper = floorMapper;
    }

    public List<FloorDTO> getAllFloors() {
        return floorMapper.toDTOList(floorRepository.findAll());
    }

    public FloorDTO getFloorById(Long id) {
        return floorMapper.toDTO(floorRepository.findById(id).orElse(null));
    }

    public List<FloorDTO> getFloorsByBuildingId(Long buildingId) {
        return floorMapper.toDTOList(floorRepository.findFloorsByBuildingId(buildingId));
    }

    public FloorDTO updateFloorNumber(Long id, Integer floorNumber) {
        Floor floor = floorRepository.findById(id).orElse(null);
        if (floor != null) {
            floor.setFloorNumber(floorNumber);
            floorRepository.save(floor);
            return floorMapper.toDTO(floor);
        } else {
            throw new ResourceNotFoundException("Floor not found with id " + id);
        }
    }

    public void deleteFloor(Long id) {
        floorRepository.deleteById(id);
    }

    public FloorDTO addFloor(FloorDTO floorDTO) {
        Floor floor = floorMapper.toEntity(floorDTO);
        FloorDTO savedFloorDTO = floorMapper.toDTO(floorRepository.save(floor));
        return savedFloorDTO;
    }
}
