package ba.nwt.keycard.RequestService.models.Log;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ba.nwt.keycard.RequestService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.repositories.UserRepository;

@Component
public class LogMapper {

    private final UserRepository userRepository;

    @Autowired
    public LogMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Long roomId, String entryType, Long userId, String description
    public LogDTO toDTO(Log log) {
        LogDTO dto = new LogDTO(log.getRoomId(), log.getEntryType(), log.getUser().getId(), log.getDescription());
        return dto;
    }

    public List<LogDTO> toDTOList(List<Log> logs) {
        return logs.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Log toEntity(LogDTO dto) {
        Log log = new Log();
        log.setRoomId(dto.getRoomId());
        log.setEntryType(dto.getEntryType());
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));
        log.setUser(user);
        log.setDescription(dto.getDescription());
        return log;
    }
}
