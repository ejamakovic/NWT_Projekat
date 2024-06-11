package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.models.Log;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.models.dtos.LogDTO;
import ba.nwt.keycard.RequestService.models.dtos.LogMapper;
import ba.nwt.keycard.RequestService.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogService {

    private LogRepository logRepository;
    private final LogMapper logMapper;

    @Autowired
    public LogService(LogRepository logRepository, LogMapper logMapper) {
        this.logRepository = logRepository;
        this.logMapper = logMapper;
    }

    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    public Log getLogById(Long id) {
        Optional<Log> log = logRepository.findById(id);
        /* LogDTO logDTO = logMapper.toDTO(log.orElse(null)); */
        return log.orElse(null);
    }

    public LogDTO addLog(LogDTO logDTO) {
        Log log = logMapper.toEntity(logDTO);
        LogDTO savedLogDTO = logMapper.toDTO(logRepository.save(log));
        return savedLogDTO;
    }

    public boolean deleteLogById(Long id) {
        Optional<Log> logOptional = logRepository.findById(id);
        if (logOptional.isPresent()) {
            Log log = logOptional.get();
            logRepository.delete(log);
            return true;
        }
        return false;
    }

}
