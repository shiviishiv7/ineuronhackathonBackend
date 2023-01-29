package com.hack.hackathon.exceptionsHandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RecordNotFound.class)
    public ResponseEntity<ErrorResponse> errorRecordNotFound(RecordNotFound recordNotFound){
        long currentTimeMillis = System.currentTimeMillis();
        ErrorResponse errorResponse = new ErrorResponse(recordNotFound.getMessage(),
                HttpStatus.NOT_FOUND.value(),currentTimeMillis);
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> usernameNotFoundException(UsernameNotFoundException recordNotFound){
        long currentTimeMillis = System.currentTimeMillis();
        ErrorResponse errorResponse = new ErrorResponse(recordNotFound.getMessage(),
                HttpStatus.NOT_FOUND.value(),currentTimeMillis);
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> numberFormatException(NumberFormatException numberFormatException){
        long currentTimeMillis = System.currentTimeMillis();
        ErrorResponse errorResponse = new ErrorResponse(numberFormatException.getMessage(),
                HttpStatus.NOT_FOUND.value(),currentTimeMillis);
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentialsException(BadCredentialsException badCredentialsException){
        long currentTimeMillis = System.currentTimeMillis();
        ErrorResponse errorResponse = new ErrorResponse(badCredentialsException.getMessage(),
                HttpStatus.NOT_FOUND.value(),currentTimeMillis);
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RecordFound.class)
    public ResponseEntity<ErrorResponse> errorRecordFound(RecordFound recordNotFound){
        long currentTimeMillis = System.currentTimeMillis();
        ErrorResponse errorResponse = new ErrorResponse(recordNotFound.getMessage(),
                HttpStatus.NOT_FOUND.value(),currentTimeMillis);
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }





    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> allExceptionHandling(Exception exception){
        long currentTimeMillis = System.currentTimeMillis();
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),currentTimeMillis);
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }


}
