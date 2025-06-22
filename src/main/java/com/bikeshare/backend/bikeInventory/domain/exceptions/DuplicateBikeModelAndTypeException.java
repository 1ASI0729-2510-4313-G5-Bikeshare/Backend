package com.bikeshare.backend.bikeInventory.domain.exceptions;

import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeTypes;

public class DuplicateBikeModelAndTypeException extends RuntimeException {
    public DuplicateBikeModelAndTypeException(String model, BikeTypes type) {
        super("Bike with model %s and type %s already exists".formatted(model, type));
    }
}
