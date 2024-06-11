package ba.nwt.keycard.RoomService.services;

import ba.nwt.keycard.RoomService.RibbonProxies.PermissionServiceProxy;
import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.models.PermissionDTOs.PermissionDTO;
import ba.nwt.keycard.RoomService.models.Room.FullRoomDTO;
import ba.nwt.keycard.RoomService.models.Room.Room;
import ba.nwt.keycard.RoomService.models.Room.RoomDTO;
import ba.nwt.keycard.RoomService.models.Room.RoomMapper;
import ba.nwt.keycard.RoomService.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final PermissionServiceProxy permissionServiceProxy;

    private final JdbcTemplate jdbcTemplate;

    public List<FullRoomDTO> findCustomRoomsByBuildingFloorAndRoomIds(Long buildingIds[], Long floorIds[],
            Long roomIds[]) {
        String sql = "SELECT " +
                "r.id AS room_id, " +
                "r.name AS room_name, " +
                "f.id AS floor_id, " +
                "f.floor_number AS floor_number, " +
                "b.id AS building_id, " +
                "b.name AS building_name " +
                "FROM rooms r " +
                "JOIN floors f ON r.floor_id = f.id " +
                "JOIN buildings b ON f.building_id = b.id WHERE ";

        List<String> conditions = new ArrayList<>();
        List<Object> params = new ArrayList<>(); // Declare params variable here

        // Assuming roomIds, floorIds, and buildingIds are defined somewhere above
        if (roomIds.length > 0) {
            conditions.add("r.id IN (" + String.join(",", Collections.nCopies(roomIds.length, "?")) + ")");
            Collections.addAll(params, roomIds);
        }
        if (floorIds.length > 0) {
            conditions.add("f.id IN (" + String.join(",", Collections.nCopies(floorIds.length, "?")) + ")");
            Collections.addAll(params, floorIds);
        }
        if (buildingIds.length > 0) {
            conditions.add("b.id IN (" + String.join(",", Collections.nCopies(buildingIds.length, "?")) + ")");
            Collections.addAll(params, buildingIds);
        }

        sql += String.join(" OR ", conditions);

        return jdbcTemplate.query(sql, (rs, rowNum) -> new FullRoomDTO(
                rs.getLong("room_id"),
                rs.getString("room_name"),
                rs.getLong("floor_id"),
                rs.getLong("building_id"),
                rs.getInt("floor_number"),
                rs.getString("building_name")), params.toArray());
    }

    @Autowired
    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper,
            PermissionServiceProxy permissionServiceProxy, JdbcTemplate jdbcTemplate) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
        this.permissionServiceProxy = permissionServiceProxy;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FullRoomDTO> getRoomsWithKeycard(Long keycardId) {
        // ubaciti provjeru ako je validna kartica

        List<PermissionDTO> permissionDTOs = permissionServiceProxy.getKeycardPermissions(keycardId);
        System.out.println(permissionDTOs);

        Long[] buildingIds = new Long[permissionDTOs.size()];
        Long[] floorIds = new Long[permissionDTOs.size()];
        Long[] roomIds = new Long[permissionDTOs.size()];

        for (int i = 0; i < permissionDTOs.size(); i++) {
            PermissionDTO dto = permissionDTOs.get(i);
            buildingIds[i] = dto.getBuildingId();
            floorIds[i] = dto.getFloorId();
            roomIds[i] = dto.getRoomId();
        }

        // Example usage
        System.out.println(Arrays.toString(buildingIds));
        System.out.println(Arrays.toString(floorIds));
        System.out.println(Arrays.toString(roomIds));

        List<FullRoomDTO> fullRoomDTOs = findCustomRoomsByBuildingFloorAndRoomIds(buildingIds, floorIds,
                roomIds);
        System.out.println(fullRoomDTOs);

        return fullRoomDTOs;
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
