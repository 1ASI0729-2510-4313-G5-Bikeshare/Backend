package com.bikeshare.backend.bikeInventory.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value object representing the geographic coordinates of a bike.
 * @summary
 * This value object is used to represent the latitude and longitude coordinates of a bike. It is an embeddable object
 * that encapsulates validation rules for valid coordinate ranges and ensures the location is within Lima Metropolitana.
 * It throws an IllegalArgumentException if the coordinates are invalid or outside the defined region.
 * @param latitude The latitude of the bike location. Must be between -90 and 90.
 * @param longitude The longitude of the bike location. Must be between -180 and 180.
 * @see IllegalArgumentException
 * @since 1.0
 */
@Embeddable
public record Coordinates(Float latitude, Float longitude) {

    public Coordinates {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Invalid latitude");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Invalid longitude");
        }
        if (!isWithinLima(latitude, longitude)) {
            throw new IllegalArgumentException("Coordinates must be within Lima Metropolitana");
        }
    }

    private static boolean isWithinLima(float lat, float lng) {
        return lat >= -12.25 && lat <= -11.75 &&
                lng >= -77.20 && lng <= -76.80;
    }
}
