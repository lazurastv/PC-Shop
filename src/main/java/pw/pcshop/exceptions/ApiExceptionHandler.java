package pw.pcshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<Object> handelApiRequestException(RuntimeException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(e.getMessage());
        return new ResponseEntity<>(apiException, badRequest);
    }
}
