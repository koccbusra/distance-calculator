package com.example.distancecalculator.response;

import com.example.distancecalculator.request.DistanceRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LocationResponse {
    public String postCode;
    public String latitude;
    public String longitude;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocationResponse)) {
            return false;
        }
        LocationResponse lr = (LocationResponse)obj;
        return lr.postCode.equals(this.postCode)
                && lr.latitude.equals(this.latitude)
                && lr.longitude.equals(this.longitude);
    }

}
