package com.example.distancecalculator.service;

import com.example.distancecalculator.calculator.DistanceCalculator;
import com.example.distancecalculator.exception.LocationNotFoundException;
import com.example.distancecalculator.model.LocationModel;
import com.example.distancecalculator.repository.LocationRepository;
import com.example.distancecalculator.request.DistanceRequest;
import com.example.distancecalculator.request.LocationRequest;
import com.example.distancecalculator.response.DistanceResponse;
import com.example.distancecalculator.response.LocationResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final DistanceCalculator distanceCalculator;

    public LocationService(LocationRepository locationRepository, DistanceCalculator distanceCalculator) {
        this.locationRepository = locationRepository;
        this.distanceCalculator = distanceCalculator;
    }

    public DistanceResponse getDistance(DistanceRequest distanceRequest) {
        Optional<LocationModel> firstLocation = locationRepository.findById(distanceRequest.firstPostCode);
        if(!firstLocation.isPresent())
            throw new LocationNotFoundException(distanceRequest.firstPostCode);

        Optional<LocationModel> secondLocation = locationRepository.findById(distanceRequest.secondPostCode);
        if(!secondLocation.isPresent())
            throw new LocationNotFoundException(distanceRequest.secondPostCode);

        double distance = distanceCalculator.calculateDistance(
                firstLocation.get().getLatAsDouble(),
                firstLocation.get().getLngAsDouble(),
                secondLocation.get().getLatAsDouble(),
                secondLocation.get().getLngAsDouble());

        return new DistanceResponse(
                new LocationResponse(firstLocation.get().getPostcode(), firstLocation.get().getLatitude(), firstLocation.get().getLongitude()),
                new LocationResponse(secondLocation.get().getPostcode(), secondLocation.get().getLatitude(),secondLocation.get().getLongitude()),
                distance);
    }

    public LocationResponse updateLocation(LocationRequest locationRequest) {

        Optional<LocationModel> location = locationRepository.findById(locationRequest.getPostcode());
        if(!location.isPresent())
            throw new LocationNotFoundException(locationRequest.getPostcode());

        LocationModel locationModel = new LocationModel();
        locationModel.setId(location.get().getId());
        locationModel.setPostcode(location.get().getPostcode());
        locationModel.setLatitude(locationRequest.getLatitude());
        locationModel.setLongitude(locationRequest.getLongitude());

        LocationModel updatedLocation = locationRepository.save(locationModel);

        return new LocationResponse(updatedLocation.getPostcode(),
                updatedLocation.getLatitude(),
                updatedLocation.getLongitude());
    }
}
