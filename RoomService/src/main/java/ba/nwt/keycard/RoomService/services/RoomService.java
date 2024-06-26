package ba.nwt.keycard.RoomService.services;

import ba.nwt.keycard.RoomService.RibbonProxies.PermissionServiceProxy;
import ba.nwt.keycard.RoomService.RibbonProxies.RequestServiceProxy;
import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.GeneralException;
import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions.UnathorizedAccessException;
import ba.nwt.keycard.RoomService.models.PermissionServiceDTOs.PermissionDTO;
import ba.nwt.keycard.RoomService.models.RequestServiceDTOs.LogDTO;
import ba.nwt.keycard.RoomService.models.Room.FullRoomDTO;
import ba.nwt.keycard.RoomService.models.Room.Room;
import ba.nwt.keycard.RoomService.models.Room.RoomDTO;
import ba.nwt.keycard.RoomService.models.Room.RoomMapper;
import ba.nwt.keycard.RoomService.models.TempAccessGrant.TempAccessGrantDTO;
import ba.nwt.keycard.RoomService.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final PermissionServiceProxy permissionServiceProxy;
    private final RequestServiceProxy requestServiceProxy;
    private final TempAccessGrantsService tempAccessGrantsService;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper,
            PermissionServiceProxy permissionServiceProxy, RequestServiceProxy requestServiceProxy,
            JdbcTemplate jdbcTemplate, TempAccessGrantsService tempAccessGrantsService) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
        this.permissionServiceProxy = permissionServiceProxy;
        this.requestServiceProxy = requestServiceProxy;
        this.jdbcTemplate = jdbcTemplate;
        this.tempAccessGrantsService = tempAccessGrantsService;
    }

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

    public List<FullRoomDTO> findLockedRoomsByBuildingFloorAndRoomIds(Long buildingIds[], Long floorIds[],
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
            conditions.add("r.id NOT IN (" + String.join(",", Collections.nCopies(roomIds.length, "?")) + ")");
            Collections.addAll(params, roomIds);
        }
        if (floorIds.length > 0) {
            conditions.add("f.id NOT IN (" + String.join(",", Collections.nCopies(floorIds.length, "?")) + ")");
            Collections.addAll(params, floorIds);
        }
        if (buildingIds.length > 0) {
            conditions.add("b.id NOT IN (" + String.join(",", Collections.nCopies(buildingIds.length, "?")) + ")");
            Collections.addAll(params, buildingIds);
        }

        sql += String.join(" AND ", conditions);

        return jdbcTemplate.query(sql, (rs, rowNum) -> new FullRoomDTO(
                rs.getLong("room_id"),
                rs.getString("room_name"),
                rs.getLong("floor_id"),
                rs.getLong("building_id"),
                rs.getInt("floor_number"),
                rs.getString("building_name")), params.toArray());
    }

    public List<FullRoomDTO> getRoomsWithKeycard(Long keycardId) {
        try {
            List<PermissionDTO> permissionDTOs = permissionServiceProxy.getKeycardPermissions(keycardId);
            System.out.println(permissionDTOs);
            List<Long> buildingIds = new ArrayList<>();
            List<Long> floorIds = new ArrayList<>();
            List<Long> roomIds = new ArrayList<>();

            for (PermissionDTO dto : permissionDTOs) {
                buildingIds.add(dto.getBuildingId());
                floorIds.add(dto.getFloorId());
                roomIds.add(dto.getRoomId());
            }

            try {
                // get userIdByKeycardId
                Long userId = requestServiceProxy.getUserIdByCardId(keycardId);
                System.out.println("user" + userId);
                // get all rooms that the user has grants to

                List<TempAccessGrantDTO> accessGrants = tempAccessGrantsService.getTempAccessGrantsByUserId(userId);

                LocalDateTime timestamp = LocalDateTime.now();
                for (TempAccessGrantDTO accessGrant : accessGrants) {
                    if (accessGrant.getTimestamp().plusMinutes(30).isAfter(timestamp))
                        roomIds.add(accessGrant.getRoomId());
                }

                try {
                    // Example usage
                    // convert to Long[]
                    Long[] buildingIdsArr = buildingIds.toArray(new Long[0]);
                    Long[] floorIdsArr = floorIds.toArray(new Long[0]);
                    Long[] roomIdsArr = roomIds.toArray(new Long[0]);

                    System.out.println(Arrays.toString(buildingIdsArr));
                    System.out.println(Arrays.toString(floorIdsArr));
                    System.out.println(Arrays.toString(roomIdsArr));
                    List<FullRoomDTO> fullRoomDTOs = findCustomRoomsByBuildingFloorAndRoomIds(buildingIdsArr,
                            floorIdsArr,
                            roomIdsArr);

                    System.out.println(fullRoomDTOs);

                    return fullRoomDTOs;
                } catch (Exception e) {
                    // Handle other exceptions
                    throw new GeneralException("An error occurred while fetching rooms.");
                }

            } catch (feign.FeignException.NotFound e) {
                throw new ResourceNotFoundException("User not found with keycard id " + keycardId);
            }

        } catch (feign.FeignException.NotFound e) {
            throw new ResourceNotFoundException("User not found with keycard id " + keycardId);
        } catch (Exception e) {
            // Handle other exceptions
            throw new GeneralException("An error occurred while getting permissions.");
        }
    }

    public List<FullRoomDTO> getLockedRoomsWithKeycard(Long keycardId) {
        try {
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
            try {
                List<FullRoomDTO> fullRoomDTOs = findLockedRoomsByBuildingFloorAndRoomIds(buildingIds, floorIds,
                        roomIds);
                System.out.println(fullRoomDTOs);

                return fullRoomDTOs;
            } catch (Exception e) {
                // Handle other exceptions
                throw new GeneralException("An error occurred while fetching rooms.");
            }

        } catch (feign.FeignException.NotFound e) {
            throw new ResourceNotFoundException("Keycard with id " + keycardId + " not found.");
        } catch (Exception e) {
            // Handle other exceptions
            throw new GeneralException("An error occurred while getting permissions.");
        }
    }

    public LogDTO enterRoom(Long roomId, Long keycardId, String entryType) {
        // ubaciti provjeru ako je validna kartica
        try {
            String description;
            if (entryType.equals("IN")) {
                description = "User entered room";
            } else {
                description = "User exited room";
            }
            Long buildingId = roomRepository.findBuildingIdByRoomId(roomId);
            Long floorId = roomRepository.findFloorIdByRoomId(roomId);

            System.out.println(buildingId);
            System.out.println(floorId);

            Boolean hasPermission = permissionServiceProxy.checkPermission(keycardId, buildingId, floorId, roomId);
            System.out.println(hasPermission);

            // dodati provjeru, if !hasPermission, provjeri da li ima approved request
            if (hasPermission) {
                System.out.println(keycardId);
                Long userId = requestServiceProxy.getUserIdByCardId(keycardId);
                System.out.println("user" + userId);

                LocalDateTime timestamp = LocalDateTime.now();
                LogDTO logDTO = requestServiceProxy
                        .addLog(new LogDTO(timestamp, entryType, userId, description, roomId));
                logDTO.setUserId(userId);
                return logDTO;
            } else {
                System.out.println(keycardId);
                Boolean accessGranted = false;
                Long userId = requestServiceProxy.getUserIdByCardId(keycardId);
                System.out.println("user" + userId);
                List<TempAccessGrantDTO> accessGrants = tempAccessGrantsService.getTempAccessGrantsByUserId(userId);
                LogDTO logDTO = null;
                if (accessGrants.size() > 0) {
                    for (TempAccessGrantDTO accessGrant : accessGrants) {
                        if (accessGrant.getRoomId() == roomId) {
                            System.out.println(accessGrant.getRoomId());
                            LocalDateTime timestamp = LocalDateTime.now();
                            // if it's been less than 30 minutes since the access grant was created
                            if (accessGrant.getTimestamp().plusMinutes(30).isAfter(timestamp)) {
                                logDTO = requestServiceProxy
                                        .addLog(new LogDTO(timestamp, entryType, userId, description, roomId));
                                logDTO.setUserId(userId);
                                accessGranted = true;
                            } else {
                                throw new UnathorizedAccessException(
                                        "User does not have permission to enter room with keycard id "
                                                + keycardId + " and room id " + roomId);
                            }
                        }
                    }
                }
                if (!accessGranted) {
                    throw new UnathorizedAccessException("User does not have permission to enter room with keycard id "
                            + keycardId + " and room id " + roomId);
                }
                return logDTO;
            }
        } catch (feign.FeignException.NotFound e) {
            throw new ResourceNotFoundException("User not found with keycard id " + keycardId);
        }
    }

    public Long getBuildingIdByRoomId(Long roomId) {
        return roomRepository.findBuildingIdByRoomId(roomId);
    }

    public Long getFloorIdByRoomId(Long roomId) {
        return roomRepository.findFloorIdByRoomId(roomId);
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
