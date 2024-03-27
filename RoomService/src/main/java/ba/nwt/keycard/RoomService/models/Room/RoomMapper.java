package ba.nwt.keycard.RoomService.models.Room;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.models.Floor.Floor;
import ba.nwt.keycard.RoomService.repositories.FloorRepository;

@Component
public class RoomMapper {

    private static FloorRepository floorRepository;

    @Autowired
    public RoomMapper(FloorRepository floorRepository) {
        RoomMapper.floorRepository = floorRepository;
    }

    public RoomDTO toDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setName(room.getName());
        dto.setFloorId(room.getFloor().getId());
        return dto;
    }

    public List<RoomDTO> toDTOList(List<Room> rooms) {
        return rooms.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Room toEntity(RoomDTO dto) {
        Room room = new Room();
        room.setId(dto.getId());
        room.setName(dto.getName());
        Floor floor = floorRepository.findById(dto.getFloorId())
                .orElseThrow(() -> new ResourceNotFoundException("Floor not found with id " + dto.getFloorId()));
        room.setFloor(floor);
        return room;
    }
}
