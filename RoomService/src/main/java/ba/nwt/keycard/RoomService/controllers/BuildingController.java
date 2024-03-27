package ba.nwt.keycard.RoomService.controllers;

import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.models.Building.Building;
import ba.nwt.keycard.RoomService.models.Building.BuildingDTO;
import ba.nwt.keycard.RoomService.services.BuildingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingController {

    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    public ResponseEntity<List<BuildingDTO>> getAllBuildings() {
        List<BuildingDTO> buildingDTOs = buildingService.getAllBuildings();
        return ResponseEntity.ok().body(buildingDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingDTO> getBuildingById(@PathVariable("id") Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        BuildingDTO buildingDTO = buildingService.getBuildingById(id);
        if (buildingDTO != null) {
            return ResponseEntity.ok().body(buildingDTO);
        } else {
            throw new ResourceNotFoundException("Building not found with id " + id);
        }
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<BuildingDTO> updateName(@PathVariable("id") Long id, @RequestParam String name) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Building name is required");
        }

        BuildingDTO buildingDTO = buildingService.updateName(id, name);
        return ResponseEntity.ok().body(buildingDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBuilding(@PathVariable("id") Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        buildingService.deleteBuilding(id);
        return ResponseEntity.ok().body("Building deleted successfully");
    }

    @PostMapping
    public ResponseEntity<BuildingDTO> addBuilding(@Valid @RequestBody(required = false) Building building) {
        if (building == null) {
            throw new IllegalArgumentException("Building object is required");
        }

        if (building.getName() == null) {
            throw new IllegalArgumentException("Building name is required");
        }

        BuildingDTO savedBuildingDTO = buildingService.addBuilding(building);
        return ResponseEntity.ok().body(savedBuildingDTO);
    }
}
