package ba.nwt.keycard.RoomService.models.TempAccessGrant;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import ba.nwt.keycard.RoomService.models.Building.Building;
import ba.nwt.keycard.RoomService.models.Building.BuildingDTO;
import ba.nwt.keycard.RoomService.models.Building.BuildingMapper;
import ba.nwt.keycard.RoomService.repositories.BuildingRepository;
import ba.nwt.keycard.RoomService.repositories.TempAccessGrantsRepository;

public class TempAccessGrantMapper {
    private static TempAccessGrantsRepository tempAccessGrantsRepository;

    @Autowired
    public TempAccessGrantMapper(TempAccessGrantsRepository tempAccessGrantsRepository) {
        TempAccessGrantMapper.tempAccessGrantsRepository = tempAccessGrantsRepository;
    }

    public TempAccessGrantDTO toDTO(TempAccessGrant tempAccessGrant) {
        TempAccessGrantDTO dto = new TempAccessGrantDTO();
        dto.setUserId(tempAccessGrant.getUserId());
        dto.setRoomId(tempAccessGrant.getRoomId());
        dto.setTimestamp(tempAccessGrant.getTimestamp());
        return dto;
    }

    public List<TempAccessGrantDTO> toDTOList(List<TempAccessGrant> tempAccessGrants) {
        return tempAccessGrants.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TempAccessGrant toEntity(TempAccessGrantDTO dto) {
        TempAccessGrant tempAccessGrant = new TempAccessGrant();
        tempAccessGrant.setUserId(dto.getUserId());
        tempAccessGrant.setRoomId(dto.getRoomId());
        tempAccessGrant.setTimestamp(dto.getTimestamp());
        return tempAccessGrant;
    }
}
