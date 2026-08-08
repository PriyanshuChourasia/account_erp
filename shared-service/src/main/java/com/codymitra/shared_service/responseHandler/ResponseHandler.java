package com.codymitra.shared_service.responseHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ResponseHandler {

    private ResponseHandler(){
        throw new IllegalArgumentException("This class cannot be instantiated");
    }


    public static <T> ResponseEntity<Map<String,Object>> generateResponse(List<T> data, String message, HttpStatus httpStatus){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        result.put("result",data);
        map.put("message",message);
        map.put("code",httpStatus.value());
        map.put("status",httpStatus);
        map.put("data",result);
        return new ResponseEntity<>(map,httpStatus);
    }



    public static <T> ResponseEntity<Map<String,Object>> generateResponse(T data, String message, HttpStatus httpStatus){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        result.put("result",data);
        map.put("message",message);
        map.put("code",httpStatus.value());
        map.put("status",httpStatus);
        map.put("data",result);
        return new ResponseEntity<>(map,httpStatus);
    }


    public static ResponseEntity<Map<String,Object>> generateResponse(String message, HttpStatus httpStatus){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        result.put("result",message);
        map.put("message",message);
        map.put("code",httpStatus.value());
        map.put("status",httpStatus);
        map.put("data",result);
        return new ResponseEntity<>(map,httpStatus);
    }
}