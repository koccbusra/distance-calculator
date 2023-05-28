package com.example.distancecalculator.controller;

import com.example.distancecalculator.request.DistanceRequest;
import com.example.distancecalculator.request.LocationRequest;
import com.example.distancecalculator.response.DistanceResponse;
import com.example.distancecalculator.response.LocationResponse;
import com.example.distancecalculator.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class LocationController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    private final LocationService locationService;
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("getDistance")
    public ResponseEntity<DistanceResponse> getDistance(@RequestParam("firstPostcode") String firstPostcode,
                                                        @RequestParam("secondPostcode") String secondPostcode){
        log.info("getDistance request has been received firstPostcode={}, secondPostcode={}", firstPostcode, secondPostcode);

        DistanceResponse distanceResponse = locationService.getDistance(new DistanceRequest(firstPostcode, secondPostcode));

        return new ResponseEntity<>(distanceResponse, HttpStatus.OK);
    }

    @PutMapping("updateLocation")
    public ResponseEntity<LocationResponse> updateLocation(@RequestBody LocationRequest locationRequest){
        log.info("updateLocation request has been received postcode={}, latitude={}, longitude={} ",
                locationRequest.getPostcode(), locationRequest.getLatitude(), locationRequest.getLongitude());

        LocationResponse locationResponse = locationService.updateLocation(locationRequest);

        return new ResponseEntity<>(locationResponse, HttpStatus.OK);
    }
}
