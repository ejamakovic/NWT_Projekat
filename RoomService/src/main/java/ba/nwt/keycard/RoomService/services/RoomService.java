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

    public List<RoomDTO> getAllRooms() {
        return roomMapper.toDTOList(roomRepository.findAll());
    }

    public RoomDTO getRoomById(Long id) {
        return roomMapper.toDTO(roomRepository.findById(id).orElse(null));
    }

    public List<RoomDTO> getRoomsByFloorId(Long floorId) {
        return roomMapper.toDTOList(roomRepository.findRoomsByFloorId(floorId));
    }

    public List<RoomDTO> getRoomsByBuildingId(Long buildingId) {
        List<Room> rooms = roomRepository.findRoomsByBuildingIdSortedByFloorId(buildingId);
        return roomMapper.toDTOList(rooms);
    }

    public RoomDTO updateRoomName(Long id, String name) {
        Room room = roomRepository.findById(id).orElse(null);

        if (room != null) {
            room.setName(name);
            roomRepository.save(room);
            return roomMapper.toDTO(room);
        } else {
            throw new ResourceNotFoundException("Room not found with id " + id);
        }
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public RoomDTO addRoom(RoomDTO roomDTO) {
        Room room = roomMapper.toEntity(roomDTO);
        RoomDTO savedRoomDTO = roomMapper.toDTO(roomRepository.save(room));
        return savedRoomDTO;
    }
}
