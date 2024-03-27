package ba.nwt.keycard.RoomService.models.Building;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import ba.nwt.keycard.RoomService.repositories.BuildingRepository;

@ComponentScan
public class BuildingMapper {

    private static BuildingRepository buildingRepository;

    @Autowired
    public BuildingMapper(BuildingRepository buildingRepository) {
        BuildingMapper.buildingRepository = buildingRepository;
    }

    public BuildingDTO toDTO(Building building) {
        BuildingDTO dto = new BuildingDTO();
        dto.setId(building.getId());
        dto.setName(building.getName());
        return dto;
    }

    public List<BuildingDTO> toDTOList(List<Building> buildings) {
        return buildings.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Building toEntity(BuildingDTO dto) {
        Building building = new Building();
        building.setId(dto.getId());
        building.setName(dto.getName());
        return building;
    }
}
