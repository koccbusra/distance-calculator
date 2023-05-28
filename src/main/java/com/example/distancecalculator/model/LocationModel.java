package com.example.distancecalculator.model;

import com.example.distancecalculator.exception.InvalidLatitudeFormatException;
import com.example.distancecalculator.exception.InvalidLongitudeFormatException;
import com.example.distancecalculator.response.LocationResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "POSTCODELATLNG")
public class LocationModel {
    private Integer id;
    @Id
    private String postcode;
    private String latitude;
    private String longitude;

    public double getLatAsDouble(){
        try{
            return Double.parseDouble(latitude);
        }catch (NumberFormatException e){
            throw new InvalidLatitudeFormatException(postcode);
        }
    }

    public double getLngAsDouble(){
        try{
            return Double.parseDouble(longitude);
        }catch (NumberFormatException e){
            throw new InvalidLongitudeFormatException(postcode);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocationModel)) {
            return false;
        }
        LocationModel lm = (LocationModel)obj;
        return lm.postcode.equals(this.postcode)
                && lm.id.equals(this.id)
                && lm.latitude.equals(this.latitude)
                && lm.longitude.equals(this.longitude);
    }
}
