package ba.nwt.keycard.RoomService.models.Floor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.models.Building.Building;
import ba.nwt.keycard.RoomService.repositories.BuildingRepository;

@Component
public class FloorMapper {

    private final BuildingRepository buildingRepository;

    @Autowired
    public FloorMapper(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public FloorDTO toDTO(Floor floor) {
        FloorDTO dto = new FloorDTO();
        dto.setId(floor.getId());
        dto.setFloorNumber(floor.getFloorNumber());
        dto.setBuildingId(floor.getBuilding().getId());
        return dto;
    }

    public List<FloorDTO> toDTOList(List<Floor> floors) {
        return floors.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Floor toEntity(FloorDTO dto) {
        Floor floor = new Floor();
        floor.setId(dto.getId());
        floor.setFloorNumber(dto.getFloorNumber());
        Building building = buildingRepository.findById(dto.getBuildingId())
                .orElseThrow(() -> new ResourceNotFoundException("Building not found with id " + dto.getBuildingId()));
        floor.setBuilding(building);
        return floor;
    }
}
