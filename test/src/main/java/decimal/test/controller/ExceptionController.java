package decimal.test.controller;

import decimal.test.dto.ResponseDto;
import decimal.test.exception.TestException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log
public class ExceptionController {


    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity runtimeException(RuntimeException ex)
    {
        String message = ex.getMessage();

       log.info("-------- ");

        ResponseDto responseDto = new ResponseDto("TEST_500",message);
        return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = TestException.class)
    public ResponseEntity testException (TestException ex)
    {
        String message = ex.getMessage();
        String code = ex.getStatusCode();
        Object errorMessages = ex.getErrorMessages();

        ResponseDto responseDto = new ResponseDto("FAILURE",code,message,errorMessages);
        return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }
}
