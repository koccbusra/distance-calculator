package com.example.distancecalculator.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LocationRequest {
    private String postcode;
    private String latitude;
    private String longitude;
}
