package com.codymitra.shared_service.exceptionHandler.exceptions;

public class DataAlreadyExistsException extends RuntimeException{
    public DataAlreadyExistsException(String message){
        super(message);
    }
}