package ba.nwt.keycard.RoomService.controllers;

import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.models.Building.Building;
import ba.nwt.keycard.RoomService.models.Floor.Floor;
import ba.nwt.keycard.RoomService.models.Room.Room;
import ba.nwt.keycard.RoomService.models.Room.RoomDTO;
import ba.nwt.keycard.RoomService.services.FloorService;
import ba.nwt.keycard.RoomService.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok().body(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable("id") Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        Room room = roomService.getRoomById(id);
        if (room != null) {
            return ResponseEntity.ok().body(room);
        } else {
            throw new ResourceNotFoundException("Room not found with id " + id);
        }
    }

    @GetMapping("/floor/{floorId}")
    public ResponseEntity<List<Room>> getRoomsByFloorId(@PathVariable("floorId") Long floorId) {
        if (floorId == null) {
            throw new IllegalArgumentException("Floor ID is required");
        }

        if (floorId <= 0) {
            throw new IllegalArgumentException("Floor ID must be a positive number");
        }

        List<Room> rooms = roomService.getRoomsByFloorId(floorId);

        if (rooms != null) {
            return ResponseEntity.ok().body(rooms);
        } else {
            throw new ResourceNotFoundException("No rooms found for floor with id " + floorId);
        }
    }

    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<Room>> getRoomsByBuildingId(@PathVariable("buildingId") Long buildingId) {
        if (buildingId == null) {
            throw new IllegalArgumentException("Building ID is required");
        }

        if (buildingId <= 0) {
            throw new IllegalArgumentException("Building ID must be a positive number");
        }

        List<Room> rooms = roomService.getRoomsByBuildingId(buildingId);

        if (rooms != null) {
            return ResponseEntity.ok().body(rooms);
        } else {
            throw new ResourceNotFoundException("No rooms found for building with id " + buildingId);
        }
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<String> updateRoomName(@PathVariable("id") Long id, @RequestParam String name) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        if (name == null) {
            throw new IllegalArgumentException("Room name is required");
        }

        roomService.updateRoomName(id, name);
        return ResponseEntity.ok().body("Room name updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        roomService.deleteRoom(id);
        return ResponseEntity.ok().body("Room deleted successfully");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRoom(@Valid @RequestBody(required = false) RoomDTO roomDTO) {
        if (roomDTO == null) {
            throw new IllegalArgumentException("Request body is missing");
        }

        if (roomDTO.getName() == null) {
            throw new IllegalArgumentException("Room name is required");
        }

        Room savedRoom = roomService.addRoom(roomDTO);
        return ResponseEntity.ok().body(savedRoom);
    }
}
