package ba.nwt.keycard.RequestService.controllers.ErrorHandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errorsList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            Map<String, String> errorMap = new HashMap<>();
            String errorMessage = error.getDefaultMessage();
            errorMap.put("error", "validation");
            errorMap.put("message", errorMessage);
            errorsList.add(errorMap);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsList);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Throwable cause = ex.getRootCause();
        if (cause instanceof SQLIntegrityConstraintViolationException) {
            SQLIntegrityConstraintViolationException sqlEx = (SQLIntegrityConstraintViolationException) cause;
            String errorMessage = sqlEx.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return ResponseEntity.badRequest().body("Internal server error occurred.");
    }

}
