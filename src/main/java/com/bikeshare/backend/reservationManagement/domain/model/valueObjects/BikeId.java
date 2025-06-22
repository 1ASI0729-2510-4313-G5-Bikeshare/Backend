package com.bikeshare.backend.reservationManagement.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record BikeId(Long bikeId) {
    public BikeId {
        if (bikeId == null || bikeId < 1) {
            throw new IllegalArgumentException("Bike id cannot be null or less than 1");
        }
    }
}
