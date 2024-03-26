package ba.nwt.keycard.RoomService.controllers;

import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.models.Building;
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
    public ResponseEntity<List<Building>> getAllBuildings() {
        List<Building> buildings = buildingService.getAllBuildings();
        return ResponseEntity.ok().body(buildings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBuildingById(@PathVariable("id") Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        Building building = buildingService.getBuildingById(id);
        if (building != null) {
            return ResponseEntity.ok().body(building);
        } else {
            throw new ResourceNotFoundException("Building not found with id " + id);
        }
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<String> updateName(@PathVariable("id") Long id, @RequestParam String name) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Building name is required");
        }

        buildingService.updateName(id, name);
        return ResponseEntity.ok().body("Building name updated successfully");
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
    public ResponseEntity<?> addBuilding(@Valid @RequestBody(required = false) Building building) {
        if (building == null) {
            throw new IllegalArgumentException("Building object is required");
        }

        if (building.getName() == null) {
            throw new IllegalArgumentException("Building name is required");
        }

        Building savedBuilding = buildingService.addBuilding(building);
        return ResponseEntity.ok().body(savedBuilding);
    }
}
