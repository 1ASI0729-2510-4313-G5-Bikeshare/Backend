package com.bikeshare.backend.bikeInventory.domain.exceptions;

public class BikePersistenceException extends RuntimeException {
    public BikePersistenceException(String message) {
        super(message);
    }
}
