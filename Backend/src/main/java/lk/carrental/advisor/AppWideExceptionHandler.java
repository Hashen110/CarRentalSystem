package lk.carrental.advisor;

import lk.carrental.util.StandardResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity handleException(RuntimeException ex) {
        if (ex.getMessage().startsWith("constraint") || ex.getMessage().contains("invalid")){
            return new ResponseEntity(new StandardResponse(400, "User Error", ex.getMessage()), HttpStatus.OK);
        }
        return new ResponseEntity(new StandardResponse(500, "Server Error", ex.getMessage()), HttpStatus.OK);
    }
}
