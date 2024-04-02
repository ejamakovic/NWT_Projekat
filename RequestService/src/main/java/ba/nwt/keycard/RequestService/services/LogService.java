package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.models.Log;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public List<Log> getAllLogs(){
        return logRepository.findAll();
    }

    public Log getLogById(Long id){
        Optional<Log> log = logRepository.findById(id);
        return log.orElse(null);
    }

    public Log createLog(Log log){
        return logRepository.save(log);
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
