package com.example.distancecalculator.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends RuntimeException{
    Logger log = LoggerFactory.getLogger(this.getClass());

    public LocationNotFoundException(String postcode){
        super("Invalid location for the postcode: " + postcode);
        log.error("LocationNotFoundException Invalid location for the postcode: {}", postcode);
    }
}