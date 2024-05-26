package com.example.system_events.Services;

import com.example.system_events.Models.Log;
import com.example.system_events.Repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    public Log getLogById(Long id) {
        return logRepository.findById(id).orElse(null);
    }

    public Log addLog(Log log) {
        return logRepository.save(log);
    }
}