package com.bikeshare.backend.bikeInventory.domain.exceptions;

public class BikeNotFoundException extends RuntimeException {
    public BikeNotFoundException(Long id) {
        super("Bike with id %s not found".formatted(id));
    }
}
