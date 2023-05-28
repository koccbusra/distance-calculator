package com.example.distancecalculator.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DistanceCalculatorTest {
    private final double EPSILONE = 0.00000001d;
    private DistanceCalculator distanceCalculator = new DistanceCalculator();

    private static Stream<Arguments> latLonOfSeveralLocations() {
        return Stream.of(
                Arguments.of(57.144156, -2.114864, 57.137871,-2.121487, 0.80502516430851),
                Arguments.of(40.730610, -73.93524257,34.052235, -118.243683, 3941.5661577805613),
                Arguments.of(39.925533, 32.866287, 41.015137, 28.979530, 350.35699187889),
                Arguments.of(53.483959, -2.244644, 51.509865, -0.118092, 262.47759367795),
                Arguments.of(41.902782, 12.496366, 41.902782, 12.496366, 0.0)
        );
    }
    @ParameterizedTest
    @MethodSource("latLonOfSeveralLocations")
    void calculateDistance_LatLonAreGiven_ReturnValidDistance(double lat1, double lon1, double lat2, double lon2, double expected){
        //Act
        double actualDistance = distanceCalculator.calculateDistance(lat1, lon1, lat2, lon2);

        //Assert
        Assertions.assertEquals(expected, actualDistance, EPSILONE);
    }
}