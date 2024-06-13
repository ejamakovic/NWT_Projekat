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
        return tempAccessGrantMapper.toDTO(repository.save(tempAccessGrantMapper.toEntity(tempAccessGrantDTO)));
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

    public void deleteTempAccessGrant(Long id) {
        repository.deleteById(id);
    }

}
