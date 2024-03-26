package ba.nwt.keycard.RoomService.controllers;

import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.models.Building;
import ba.nwt.keycard.RoomService.models.Floor;
import ba.nwt.keycard.RoomService.services.FloorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/floors")
public class FloorController {

    private final FloorService floorService;

    @Autowired
    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    @GetMapping
    public ResponseEntity<List<Floor>> getAllFloors() {
        List<Floor> floors = floorService.getAllFloors();
        return ResponseEntity.ok().body(floors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFloorById(@PathVariable("id") Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        Floor floor = floorService.getFloorById(id);
        if (floor != null) {
            return ResponseEntity.ok().body(floor);
        } else {
            throw new ResourceNotFoundException("Floor not found with id " + id);
        }
    }

    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<Floor>> getAllFloorsByBuildingId(@PathVariable("buildingId") Long buildingId) {
        if (buildingId == null) {
            throw new IllegalArgumentException("Building ID is required");
        }

        if (buildingId <= 0) {
            throw new IllegalArgumentException("Building ID must be a positive number");
        }

        List<Floor> floors = floorService.getAllFloorsByBuildingId(buildingId);
        return ResponseEntity.ok().body(floors);
    }

    @PutMapping("/{id}/floorNumber")
    public ResponseEntity<String> updateFloorNumber(@PathVariable("id") Long id, @RequestParam Integer floorNumber) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        if (floorNumber == null) {
            throw new IllegalArgumentException("Floor number is required");
        }

        floorService.updateFloorNumber(id, floorNumber);
        return ResponseEntity.ok().body("Floor number updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFloor(@PathVariable("id") Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        floorService.deleteFloor(id);
        return ResponseEntity.ok().body("Floor deleted successfully");
    }

    @PostMapping
    public ResponseEntity<?> addFloor(@Valid @RequestBody(required = false) Floor floor) {
        if (floor == null) {
            throw new IllegalArgumentException("Request body is missing");
        }

        if (floor.getFloorNumber() == null) {
            throw new IllegalArgumentException("Floor number is required");
        }

        Floor savedFloor = floorService.addFloor(floor);
        return ResponseEntity.ok().body(savedFloor);
    }
}
