package ba.nwt.keycard.RequestService.controllers;

import ba.nwt.keycard.RequestService.models.Log.Log;
import ba.nwt.keycard.RequestService.models.Log.LogDTO;
import ba.nwt.keycard.RequestService.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping
    public ResponseEntity<List<Log>> getAllLogs() {
        return new ResponseEntity<>(logService.getAllLogs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Log> getLogById(@PathVariable Long id) {
        return new ResponseEntity<>(logService.getLogById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Log> addLog(@Valid @RequestBody LogDTO logDTO) {
        return new ResponseEntity<>(logService.addLog(logDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLog(@PathVariable Long id) {
        boolean deleted = logService.deleteLogById(id);
        if (deleted) {
            return ResponseEntity.ok("Log with id " + id + " has been deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Log not found with id: " + id);
        }
    }

}
