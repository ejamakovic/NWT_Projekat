package ba.nwt.keycard.RoomService.services;

import ba.nwt.keycard.RoomService.models.Building;
import ba.nwt.keycard.RoomService.repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    public Building getBuildingById(Long id) {
        return buildingRepository.findById(id).orElse(null);
    }

    public void updateName(Long id, String name) {
        Building building = buildingRepository.findById(id).orElse(null);
        if (building != null) {
            building.setName(name);
            buildingRepository.save(building);
        }
    }

    public void deleteBuilding(Long id) {
        buildingRepository.deleteById(id);
    }

    public Building addBuilding(Building building) {
        return buildingRepository.save(building);
    }
}
