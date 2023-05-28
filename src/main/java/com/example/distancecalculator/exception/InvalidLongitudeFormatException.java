package com.example.distancecalculator.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidLongitudeFormatException extends RuntimeException{
    Logger log = LoggerFactory.getLogger(this.getClass());

    public InvalidLongitudeFormatException(String postcode){
        super("Longitude format isn't correct for the postcode: " + postcode);
        log.error("InvalidLongitudeFormatException Longitude format isn't correct for the postcode: {}", postcode);
    }
}