package ba.nwt.keycard.RoomService.controllers;

import ba.nwt.keycard.RoomService.RibbonProxies.RequestServiceProxy;
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
    private final RequestServiceProxy requestServiceProxy;

    @Autowired
    public RoomController(RoomService roomService, RequestServiceProxy requestServiceProxy) {
        this.roomService = roomService;
        this.requestServiceProxy = requestServiceProxy;
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> roomDTOs = roomService.getAllRooms();
        return ResponseEntity.ok().body(roomDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable("id") Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        RoomDTO roomDTO = roomService.getRoomById(id);
        if (roomDTO != null) {
            return ResponseEntity.ok().body(roomDTO);
        } else {
            throw new ResourceNotFoundException("Room not found with id " + id);
        }
    }

    @GetMapping("/floor/{floorId}")
    public ResponseEntity<List<RoomDTO>> getRoomsByFloorId(@PathVariable("floorId") Long floorId) {
        if (floorId == null) {
            throw new IllegalArgumentException("Floor ID is required");
        }

        if (floorId <= 0) {
            throw new IllegalArgumentException("Floor ID must be a positive number");
        }

        List<RoomDTO> roomDTOs = roomService.getRoomsByFloorId(floorId);

        if (roomDTOs != null) {
            return ResponseEntity.ok().body(roomDTOs);
        } else {
            throw new ResourceNotFoundException("No rooms found for floor with id " + floorId);
        }
    }

    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<RoomDTO>> getRoomsByBuildingId(@PathVariable("buildingId") Long buildingId) {
        if (buildingId == null) {
            throw new IllegalArgumentException("Building ID is required");
        }

        if (buildingId <= 0) {
            throw new IllegalArgumentException("Building ID must be a positive number");
        }

        List<RoomDTO> roomDTOs = roomService.getRoomsByBuildingId(buildingId);

        if (roomDTOs != null) {
            return ResponseEntity.ok().body(roomDTOs);
        } else {
            throw new ResourceNotFoundException("No rooms found for building with id " + buildingId);
        }
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<RoomDTO> updateRoomName(@PathVariable("id") Long id, @RequestParam String name) {
        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }

        if (name == null) {
            throw new IllegalArgumentException("Room name is required");
        }

        RoomDTO updatedRoomDTO = roomService.updateRoomName(id, name);

        return ResponseEntity.ok().body(updatedRoomDTO);
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

    @PostMapping("/")
    public ResponseEntity<RoomDTO> addRoom(@Valid @RequestBody(required = false) RoomDTO roomDTO) {
        if (roomDTO == null) {
            throw new IllegalArgumentException("Request body is missing");
        }

        if (roomDTO.getName() == null) {
            throw new IllegalArgumentException("Room name is required");
        }

        RoomDTO savedRoomDTO = roomService.addRoom(roomDTO);
        return ResponseEntity.ok().body(savedRoomDTO);
    }

    @GetMapping("/testFeignRequest/{id}")
    public ResponseEntity<String> testFeignRequest(@PathVariable("id") Long id) {
        String response = requestServiceProxy.testFeign(id);
        return ResponseEntity.ok().body(response);
    }
}
