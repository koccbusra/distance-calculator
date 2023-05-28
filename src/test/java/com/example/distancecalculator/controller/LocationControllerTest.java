package com.example.distancecalculator.controller;

import com.example.distancecalculator.request.DistanceRequest;
import com.example.distancecalculator.request.LocationRequest;
import com.example.distancecalculator.response.DistanceResponse;
import com.example.distancecalculator.response.LocationResponse;
import com.example.distancecalculator.service.LocationService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
class LocationControllerTest {

    @Mock
    private LocationService locationServiceMock;

    @InjectMocks
    private LocationController locationController;
    @Test
    void getDistance_WhenValidPostCodesAreGiven_ReturnsValidResponseEntity() {
        //Arrange
        DistanceResponse distanceResponse = new DistanceResponse(
                new LocationResponse("AB10 1XG","57.144156", "-2.114864"),
                new LocationResponse("AB10 6RN","57.137871", "-2.121487"),
                0.805025164);

        DistanceRequest distanceRequest = new DistanceRequest("AB10 1XG","AB10 6RN");

        Mockito.when(locationServiceMock.getDistance(eq(distanceRequest))).thenReturn(distanceResponse);

        //Act
        ResponseEntity<DistanceResponse> responseEntity = locationController.getDistance("AB10 1XG", "AB10 6RN");

        //Assert
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(distanceResponse.getFirstLocation().getLatitude(), responseEntity.getBody().getFirstLocation().getLatitude());
        Assertions.assertEquals(distanceResponse.getFirstLocation().getLongitude(), responseEntity.getBody().getFirstLocation().getLongitude());
        Assertions.assertEquals(distanceResponse.getSecondLocation().getLatitude(), responseEntity.getBody().getSecondLocation().getLatitude());
        Assertions.assertEquals(distanceResponse.getSecondLocation().getLongitude(), responseEntity.getBody().getSecondLocation().getLongitude());
        Assertions.assertEquals(0.805025164, responseEntity.getBody().getDistance());
        Assertions.assertEquals("km", responseEntity.getBody().getUnit());
    }

    @Test
    void updateLocation_WhenValidLocationRequestIsGiven_ReturnsValidResponseEntity() {
        //Arrange
        LocationResponse expectedLocationResponse = new LocationResponse("AB10 1XG","57.3333", "-2.44444");

        LocationRequest locationRequest = new LocationRequest("AB10 1XG","57.3333", "-2.44444");

        Mockito.when(locationServiceMock.updateLocation(eq(locationRequest))).thenReturn(expectedLocationResponse);

        //Act
        ResponseEntity<LocationResponse> responseEntity = locationController.updateLocation(locationRequest);

        //Assert
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedLocationResponse, responseEntity.getBody());
    }
}