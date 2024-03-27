package ba.nwt.keycard.RoomService.controllers;

import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.models.Building.Building;
import ba.nwt.keycard.RoomService.models.Floor.Floor;
import ba.nwt.keycard.RoomService.models.Floor.FloorDTO;
import ba.nwt.keycard.RoomService.models.Room.RoomDTO;
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
    public ResponseEntity<List<FloorDTO>> getAllFloors() {
        List<FloorDTO> floorDTOs = floorService.getAllFloors();
        return ResponseEntity.ok().body(floorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FloorDTO> getFloorById(@PathVariable("id") Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        FloorDTO floorDTO = floorService.getFloorById(id);
        if (floorDTO != null) {
            return ResponseEntity.ok().body(floorDTO);
        } else {
            throw new ResourceNotFoundException("Floor not found with id " + id);
        }
    }

    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<FloorDTO>> getFloorsByBuildingId(@PathVariable("buildingId") Long buildingId) {
        if (buildingId == null) {
            throw new IllegalArgumentException("Building ID is required");
        }

        if (buildingId <= 0) {
            throw new IllegalArgumentException("Building ID must be a positive number");
        }

        List<FloorDTO> floorDTOs = floorService.getFloorsByBuildingId(buildingId);

        if (floorDTOs != null) {
            return ResponseEntity.ok().body(floorDTOs);
        } else {
            throw new ResourceNotFoundException("No floors found for building with id " + buildingId);
        }
    }

    @PutMapping("/{id}/floorNumber")
    public ResponseEntity<FloorDTO> updateFloorNumber(@PathVariable("id") Long id, @RequestParam Integer floorNumber) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        if (floorNumber == null) {
            throw new IllegalArgumentException("Floor number is required");
        }

        FloorDTO updatedFloorDTO = floorService.updateFloorNumber(id, floorNumber);
        return ResponseEntity.ok().body(updatedFloorDTO);
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
    public ResponseEntity<FloorDTO> addFloor(@Valid @RequestBody(required = false) FloorDTO floorDTO) {
        if (floorDTO == null) {
            throw new IllegalArgumentException("Request body is missing");
        }

        if (floorDTO.getFloorNumber() == null) {
            throw new IllegalArgumentException("Floor number is required");
        }

        FloorDTO savedFloorDTO = floorService.addFloor(floorDTO);
        return ResponseEntity.ok().body(savedFloorDTO);
    }
}
