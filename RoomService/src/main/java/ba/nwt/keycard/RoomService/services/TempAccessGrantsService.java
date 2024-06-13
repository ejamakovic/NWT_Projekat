package ba.nwt.keycard.RoomService.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import ba.nwt.keycard.RoomService.models.TempAccessGrant.TempAccessGrant;
import ba.nwt.keycard.RoomService.models.TempAccessGrant.TempAccessGrantDTO;
import ba.nwt.keycard.RoomService.models.TempAccessGrant.TempAccessGrantMapper;
import ba.nwt.keycard.RoomService.repositories.TempAccessGrantsRepository;

@Service
public class TempAccessGrantsService {

    private final TempAccessGrantMapper tempAccessGrantMapper;
    private final TempAccessGrantsRepository repository;

    public TempAccessGrantsService(TempAccessGrantMapper tempAccessGrantMapper, TempAccessGrantsRepository repository) {
        this.tempAccessGrantMapper = tempAccessGrantMapper;
        this.repository = repository;
    }

    public TempAccessGrantDTO addTempAccessGrant(TempAccessGrantDTO tempAccessGrantDTO) {
        // Convert DTO to entity
        TempAccessGrant entity = tempAccessGrantMapper.toEntity(tempAccessGrantDTO);
        // Check if there's an existing grant for the same userId and roomId
        TempAccessGrant existingGrant = repository.findByUserIdAndRoomId(entity.getUserId(), entity.getRoomId());
        if (existingGrant != null) {
            // If an existing grant is found, update its properties (except id)
            existingGrant.setTimestamp(entity.getTimestamp());
            // Save the updated entity
            entity = repository.save(existingGrant);
        } else {
            // If no existing grant, save the new entity
            entity = repository.save(entity);
        }
        // Convert the saved/updated entity back to DTO and return
        return tempAccessGrantMapper.toDTO(entity);
    }

    public TempAccessGrantDTO getTempAccessGrantById(Long id) {
        return tempAccessGrantMapper.toDTO(repository.findById(id).orElse(null));
    }

    public List<TempAccessGrantDTO> getTempAccessGrantsByUserId(Long userId) {
        return tempAccessGrantMapper.toDTOList(repository.findByUserId(userId));
    }

    public List<TempAccessGrantDTO> getTempAccessGrantsByUserIdWithRecentTimestamp(Long userId) {
        // Calculate the cutoff time for 30 minutes ago
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(30);

        return tempAccessGrantMapper.toDTOList(repository.findByUserIdWithRecentTimestamp(userId, cutoff));
    }

    public TempAccessGrantDTO getTempAccessGrantByUserIdAndRoomId(Long userId, Long roomId) {
        return tempAccessGrantMapper.toDTO(repository.findByUserIdAndRoomId(userId, roomId));
    }

    public void deleteTempAccessGrant(Long id) {
        repository.deleteById(id);
    }

}
