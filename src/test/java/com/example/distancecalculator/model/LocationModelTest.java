package com.example.distancecalculator.model;

import com.example.distancecalculator.exception.InvalidLatitudeFormatException;
import com.example.distancecalculator.exception.InvalidLongitudeFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationModelTest {

    @Test
    void getLatAsDouble_WhenValidInputIsGiven_ReturnsDouble() {
        //Arrange
        LocationModel locationModel = new LocationModel();
        locationModel.setLatitude("2.2");
        //Act
        double actualValue= locationModel.getLatAsDouble();
        //Assert
        Assertions.assertEquals(2.2, actualValue);
    }
    @Test
    void getLatAsDouble_WhenInvalidInputIsGiven_ThrowsException() {
        //Arrange
        LocationModel locationModel = new LocationModel();
        locationModel.setLatitude("");
        locationModel.setPostcode("777");
        //Act & Assert
        Exception exception = assertThrows(InvalidLatitudeFormatException.class, () -> locationModel.getLatAsDouble());
        Assertions.assertEquals("Latitude format isn't correct for the postcode: " + "777", exception.getMessage());
    }

    @Test
    void getLngAsDouble_WhenValidInputIsGiven_ReturnsDouble() {
        //Arrange
        LocationModel locationModel = new LocationModel();
        locationModel.setLongitude("2.2");
        //Act
        double actualValue= locationModel.getLngAsDouble();
        //Assert
        Assertions.assertEquals(2.2, actualValue);
    }

    @Test
    void getLngAsDouble_WhenInvalidInputIsGiven_ThrowsException() {
        //Arrange
        LocationModel locationModel = new LocationModel();
        locationModel.setLongitude("");
        locationModel.setPostcode("777");
        //Act & Assert
        Exception exception = assertThrows(InvalidLongitudeFormatException.class, () -> locationModel.getLngAsDouble());
        Assertions.assertEquals("Longitude format isn't correct for the postcode: " + "777", exception.getMessage());
    }

}