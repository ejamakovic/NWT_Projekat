package ba.nwt.keycard.RoomService.controllers;

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
            return ResponseEntity.badRequest().body("ID is required");
        }

        if (id <= 0) {
            return ResponseEntity.badRequest().body("ID must be a positive number");
        }

        Building building = buildingService.getBuildingById(id);
        if (building != null) {
            return ResponseEntity.ok().body(building);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<String> updateName(@PathVariable("id") Long id, @RequestParam String name) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ID is required");
        }

        if (id <= 0) {
            return ResponseEntity.badRequest().body("ID must be a positive number");
        }

        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().body("Name is required");
        }

        buildingService.updateName(id, name);
        return ResponseEntity.ok().body("Building name updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBuilding(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ID is required");
        }

        if (id <= 0) {
            return ResponseEntity.badRequest().body("ID must be a positive number");
        }

        buildingService.deleteBuilding(id);
        return ResponseEntity.ok().body("Building deleted successfully");
    }

    @PostMapping
    public ResponseEntity<?> addBuilding(@Valid @RequestBody(required = false) Building building) {
        if (building == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body is missing");
        }

        if (building.getName() == null) {
            return ResponseEntity.badRequest().body("Building name is required");
        }

        Building savedBuilding = buildingService.addBuilding(building);
        return ResponseEntity.ok().body(savedBuilding);
    }
}
