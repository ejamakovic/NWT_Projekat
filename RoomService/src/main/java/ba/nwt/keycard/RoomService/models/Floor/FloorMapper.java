package ba.nwt.keycard.RoomService.models.Floor;

import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.models.Building.Building;
import ba.nwt.keycard.RoomService.repositories.BuildingRepository;

public class FloorMapper {

    private static BuildingRepository buildingRepository;

    public static FloorDTO toDTO(Floor floor) {
        FloorDTO dto = new FloorDTO();
        dto.setId(floor.getId());
        dto.setFloorNumber(floor.getFloorNumber());
        dto.setBuildingId(floor.getBuilding().getId());
        return dto;
    }

    public static Floor toEntity(FloorDTO dto) {
        Floor floor = new Floor();
        floor.setId(dto.getId());
        floor.setFloorNumber(dto.getFloorNumber());
        Building building = buildingRepository.findById(dto.getBuildingId())
                .orElseThrow(() -> new ResourceNotFoundException("Building not found with id " + dto.getBuildingId()));
        floor.setBuilding(building);
        return floor;
    }
}
