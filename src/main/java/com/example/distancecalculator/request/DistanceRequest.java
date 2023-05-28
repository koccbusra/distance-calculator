package com.example.distancecalculator.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DistanceRequest {
    public String firstPostCode;
    public String secondPostCode;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DistanceRequest)) {
            return false;
        }
        DistanceRequest dr = (DistanceRequest)obj;
        return dr.firstPostCode.equals(this.firstPostCode)
                && dr.secondPostCode.equals(this.secondPostCode);
    }
}
