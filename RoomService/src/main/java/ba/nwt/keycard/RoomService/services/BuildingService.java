package ba.nwt.keycard.RoomService.services;

import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.models.Building.Building;
import ba.nwt.keycard.RoomService.models.Building.BuildingDTO;
import ba.nwt.keycard.RoomService.models.Building.BuildingMapper;
import ba.nwt.keycard.RoomService.repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
        this.buildingMapper = new BuildingMapper(buildingRepository);
    }

    public List<BuildingDTO> getAllBuildings() {
        return buildingMapper.toDTOList(buildingRepository.findAll());
    }

    public BuildingDTO getBuildingById(Long id) {
        return buildingMapper.toDTO(buildingRepository.findById(id).orElse(null));
    }

    public BuildingDTO updateName(Long id, String name) {
        Building building = buildingRepository.findById(id).orElse(null);
        if (building != null) {
            building.setName(name);
            BuildingDTO buildingDTO = buildingMapper.toDTO(buildingRepository.save(building));
            return buildingDTO;
        } else {
            throw new ResourceNotFoundException("Building not found with id " + id);
        }
    }

    public void deleteBuilding(Long id) {
        buildingRepository.deleteById(id);
    }

    public BuildingDTO addBuilding(Building building) {
        return buildingMapper.toDTO(buildingRepository.save(building));
    }
}
