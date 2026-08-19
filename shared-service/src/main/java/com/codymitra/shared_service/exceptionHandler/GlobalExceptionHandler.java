package com.codymitra.shared_service.exceptionHandler;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.MissingClaimException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String,Object>> handleBadCredentials(BadCredentialsException ex){
        String message = ex.getMessage();
        return ResponseHandler.generateResponse(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleUsernameException(UsernameNotFoundException ex){
        String message = ex.getMessage();
        log.info("exception_message {}",message);
        return ResponseHandler.generateResponse(message,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<Map<String,Object>> handleLockedException(LockedException ex){
        String message = ex.getMessage();
        return ResponseHandler.generateResponse(message,HttpStatus.LOCKED);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Map<String,Object>> handleDisabledException(DisabledException ex){
        String message = ex.getMessage();
        return ResponseHandler.generateResponse(message,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getMessage();
        return ResponseHandler.generateResponse(message, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        String message = ex.getMessage();
        return ResponseHandler.generateResponse(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Map<String,Object>> handleJwtException(ExpiredJwtException ex){
        String message = ex.getMessage();
        return ResponseHandler.generateResponse("Token is expired",HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Map<String,Object>> handleMalformedException(MalformedJwtException ex){
        String message = ex.getMessage();
        return ResponseHandler.generateResponse("Token is malformed",HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MissingClaimException.class)
    public ResponseEntity<Map<String,Object>> handleMalformedException(MissingClaimException ex){
        String message = ex.getMessage();
        return ResponseHandler.generateResponse("Calims are missing",HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleDataAlreadyExists(DataAlreadyExistsException ex) {
        String message = ex.getMessage();
        return ResponseHandler.generateResponse(message, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        String message = ex.getMessage();
        return ResponseHandler.generateResponse(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(DataNotFoundException ex) {
        String message = ex.getMessage();
        return ResponseHandler.generateResponse(message, HttpStatus.NOT_FOUND);
    }
}