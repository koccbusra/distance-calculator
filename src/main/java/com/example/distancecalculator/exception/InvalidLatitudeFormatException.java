package com.example.distancecalculator.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidLatitudeFormatException extends RuntimeException{
    Logger log = LoggerFactory.getLogger(this.getClass());

    public InvalidLatitudeFormatException(String postcode){
        super("Latitude format isn't correct for the postcode: " + postcode);
        log.error("InvalidLatitudeFormatException Latitude format isn't correct for the postcode: {}", postcode);
    }
}