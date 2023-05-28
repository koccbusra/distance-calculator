package com.example.distancecalculator.service;

import com.example.distancecalculator.calculator.DistanceCalculator;
import com.example.distancecalculator.exception.LocationNotFoundException;
import com.example.distancecalculator.model.LocationModel;
import com.example.distancecalculator.repository.LocationRepository;
import com.example.distancecalculator.request.DistanceRequest;
import com.example.distancecalculator.request.LocationRequest;
import com.example.distancecalculator.response.DistanceResponse;
import com.example.distancecalculator.response.LocationResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(SpringExtension.class)
class LocationServiceTest {

    @Mock
    private DistanceCalculator distanceCalculatorMock;

    @Mock
    private LocationRepository locationRepositoryMock;

    @InjectMocks
    private LocationService locationService;

    @Test
    void getDistance_WhenValidDistanceRequestIsGiven_ReturnsValidDistanceResponse(){
        //Arrange
        DistanceRequest distanceRequest = new DistanceRequest("AB10 1XG", "AB10 6RN");

        LocationModel firstLocation = new LocationModel();
        firstLocation.setPostcode("AB10 1XG");
        firstLocation.setLatitude("57.144156");
        firstLocation.setLongitude("-2.114864");
        Mockito.when(locationRepositoryMock.findById(eq("AB10 1XG"))).thenReturn(Optional.of(firstLocation));

        LocationModel secondLocation = new LocationModel();
        secondLocation.setPostcode("AB10 6RN");
        secondLocation.setLatitude("57.137871");
        secondLocation.setLongitude("-2.121487");
        Mockito.when(locationRepositoryMock.findById(eq("AB10 6RN"))).thenReturn(Optional.of(secondLocation));

        Mockito.when(distanceCalculatorMock.calculateDistance(
                eq(firstLocation.getLatAsDouble()),
                eq(firstLocation.getLngAsDouble()),
                eq(secondLocation.getLatAsDouble()),
                eq(secondLocation.getLngAsDouble()))
        ).thenReturn(0.805025164);

        //Act
        DistanceResponse distanceResponse = locationService.getDistance(distanceRequest);

        //Assert
        Assertions.assertEquals(firstLocation.getLatitude(), distanceResponse.getFirstLocation().getLatitude());
        Assertions.assertEquals(firstLocation.getLongitude(), distanceResponse.getFirstLocation().getLongitude());
        Assertions.assertEquals(secondLocation.getLatitude(), distanceResponse.getSecondLocation().getLatitude());
        Assertions.assertEquals(secondLocation.getLongitude(), distanceResponse.getSecondLocation().getLongitude());
        Assertions.assertEquals(0.805025164, distanceResponse.getDistance());
        Assertions.assertEquals("km", distanceResponse.getUnit());
    }

    @Test
    void getDistance_WhenFirstPostCodeIsInvalid_ThrowsLocationNotFoundException(){
        //Arrange
        String expectedMessage = "Invalid location for the postcode: WXYZ 123";

        DistanceRequest distanceRequest = new DistanceRequest("WXYZ 123", "AB10 6RN");
        Mockito.when(locationRepositoryMock.findById(eq("WXYZ 123"))).thenReturn(Optional.empty());

        //Act & Assert
        Exception exception = assertThrows(LocationNotFoundException.class, () -> locationService.getDistance(distanceRequest));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getDistance_WhenSecondPostCodeIsInvalid_ThrowsLocationNotFoundException(){
        //Arrange
        String expectedMessage = "Invalid location for the postcode: WXYZ 123";

        DistanceRequest distanceRequest = new DistanceRequest("AB10 1XG", "WXYZ 123");

        LocationModel firstLocation = new LocationModel();
        firstLocation.setPostcode("AB10 1XG");
        firstLocation.setLatitude("57.144156");
        firstLocation.setLongitude("-2.114864");
        Mockito.when(locationRepositoryMock.findById(eq("AB10 1XG"))).thenReturn(Optional.of(firstLocation));

        Mockito.when(locationRepositoryMock.findById(eq("WXYZ 123"))).thenReturn(Optional.empty());

        //Act & Assert
        Exception exception = assertThrows(LocationNotFoundException.class, () -> locationService.getDistance(distanceRequest));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void updateLocation_WhenLocationIsUpdated_ReturnsValidLocationResponse(){
        //Arrange
        LocationRequest locationRequest = new LocationRequest("AB10 1XG","55.6666","-2.77777");

        LocationResponse expectedlocationResponse = new LocationResponse("AB10 1XG","55.6666","-2.77777");

        LocationModel location = new LocationModel();
        location.setId(777);
        location.setPostcode("AB10 1XG");
        location.setLatitude("57.144156");
        location.setLongitude("-2.114864");
        Mockito.when(locationRepositoryMock.findById(eq("AB10 1XG"))).thenReturn(Optional.of(location));

        location.setPostcode("AB10 1XG");
        location.setLatitude("55.6666");
        location.setLongitude("-2.77777");

        LocationModel updatedLocation = new LocationModel();
        updatedLocation.setId(777);
        updatedLocation.setPostcode("AB10 1XG");
        updatedLocation.setLatitude("55.6666");
        updatedLocation.setLongitude("-2.77777");
        Mockito.when(locationRepositoryMock.save(eq(location))).thenReturn(updatedLocation);

        //Act
        LocationResponse locationResponse = locationService.updateLocation(locationRequest);

        //Assert
        Assertions.assertEquals(expectedlocationResponse, locationResponse);
    }

    @Test
    void updateLocation_WhenPostCodeIsInvalid_ThrowsLocationNotFoundException(){
        //Arrange
        String expectedMessage = "Invalid location for the postcode: WXYZ 123";

        LocationRequest locationRequest = new LocationRequest("WXYZ 123", "0.0", "0.0");
        Mockito.when(locationRepositoryMock.findById(eq("WXYZ 123"))).thenReturn(Optional.empty());

        //Act & Assert
        Exception exception = assertThrows(LocationNotFoundException.class, () -> locationService.updateLocation(locationRequest));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
}