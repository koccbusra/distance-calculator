package com.example.distancecalculator.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class DistanceResponse {
    LocationResponse firstLocation;
    LocationResponse secondLocation;
    Double distance;
    final String unit = "km";
}
