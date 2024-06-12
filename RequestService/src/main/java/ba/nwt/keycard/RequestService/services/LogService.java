package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.clients.RoomClient;
import ba.nwt.keycard.RequestService.models.Log.Log;
import ba.nwt.keycard.RequestService.models.Log.LogDTO;
import ba.nwt.keycard.RequestService.models.Log.LogMapper;
import ba.nwt.keycard.RequestService.repositories.LogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class LogService {

    private final LogRepository logRepository;
    private final LogMapper logMapper;
    private final RoomClient roomClient;

    @Autowired
    public LogService(LogRepository logRepository, LogMapper logMapper, RoomClient roomClient) {
        this.logRepository = logRepository;
        this.logMapper = logMapper;
        this.roomClient = roomClient;
    }

    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    public Log getLogById(Long id) {
        Optional<Log> log = logRepository.findById(id);
        return log.orElse(null);
    }

    @Transactional
    public Log addLog(@Valid LogDTO logDTO) {
        Log log = logMapper.toEntity(logDTO);
        Optional<?> room = roomClient.getRoomById(log.getRoomId());
        if(room.isPresent()){
            return logRepository.save(log);
        }
        else{
            throw new IllegalArgumentException("Room with ID " + log.getRoomId() + " does not exist.");
        }
    }

    @Transactional
    public boolean deleteLogById(Long id) {
        Optional<Log> logOptional = logRepository.findById(id);
        if (logOptional.isPresent()) {
            Log log = logOptional.get();
            logRepository.delete(log);
            return true;
        }
        return false;
    }

    public List<Log> getLogsByUserId(Long userId) {
        return logRepository.findByUserId(userId);
    }
}
