package com.amir.finalproject.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VideoNotFoundException extends RuntimeException{

    //constructor for sending a message
    public VideoNotFoundException(String message) {
        super(message);
    }

    //constructor for sending a message and its causes
    public VideoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
