package ba.nwt.keycard.RoomService.services;

import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.models.Room.Room;
import ba.nwt.keycard.RoomService.models.Room.RoomDTO;
import ba.nwt.keycard.RoomService.models.Room.RoomMapper;
import ba.nwt.keycard.RoomService.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Autowired
    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public List<Room> getRoomsByFloorId(Long floorId) {
        return roomRepository.findRoomsByFloorId(floorId);
    }

    public List<Room> getRoomsByBuildingId(Long buildingId) {
        return roomRepository.findRoomsByBuildingIdSortedByFloorId(buildingId);
    }

    public void updateRoomName(Long id, String name) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            room.setName(name);
            roomRepository.save(room);
        } else {
            throw new ResourceNotFoundException("Room not found with id " + id);
        }
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public Room addRoom(RoomDTO roomDTO) {
        Room room = roomMapper.toEntity(roomDTO);
        return roomRepository.save(room);
    }
}
