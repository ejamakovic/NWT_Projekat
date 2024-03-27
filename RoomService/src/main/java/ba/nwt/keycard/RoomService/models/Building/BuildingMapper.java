package ba.nwt.keycard.RoomService.models.Building;

public class BuildingMapper {

    public static BuildingDTO toDTO(Building building) {
        BuildingDTO dto = new BuildingDTO();
        dto.setId(building.getId());
        dto.setName(building.getName());
        return dto;
    }

    public static Building toEntity(BuildingDTO dto) {
        Building building = new Building();
        building.setId(dto.getId());
        building.setName(dto.getName());
        return building;
    }
}
