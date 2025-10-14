package springSecurityStudies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import springSecurityStudies.dto.response.ErrorResponse;
import springSecurityStudies.exception.types.DataConflictException;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<ErrorResponse> dataConflictException(DataConflictException dataConflictException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(dataConflictException.getMessage(), Instant.now()));
    }

}