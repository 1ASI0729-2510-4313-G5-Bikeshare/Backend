package com.bikeshare.backend.bikeInventory.domain.model.commands;

import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeTypes;

import java.math.BigDecimal;

/**
 * Command to create a bike.
 *
 * @param ownerId        The ID of the owner. Must not be null or less than or equal to 0.
 * @param model          The model of the bike. Must not be null, blank, or shorter than 3 characters.
 * @param type           The type of the bike (e.g., ELECTRIC, MOUNTAIN). Must not be null.
 * @param latitude       The latitude of the bike's location. Must be between -90 and 90,
 *                       and within the bounds of Lima Metropolitana.
 * @param longitude      The longitude of the bike's location. Must be between -180 and 180,
 *                       and within the bounds of Lima Metropolitana.
 * @param imageUrl       A URL pointing to an image of the bike. Must not be null or blank.
 * @param costPerMinute  The rental cost per minute. Must not be null or less than or equal to zero.
 */
public record CreateBikeCommand(Long ownerId, String model, BikeTypes type, Float latitude, Float longitude, String imageUrl, BigDecimal costPerMinute) {

    public CreateBikeCommand {
        if(ownerId == null || ownerId <= 0) {
            throw new IllegalArgumentException("ownerId cannot be null or less than or equal to 0");
        }
        if(model == null || model.isBlank() || model.length() < 3) {
            throw new IllegalArgumentException("model cannot be null or  less than 3 characters or blank");
        }
        if(type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }
        if (costPerMinute == null || costPerMinute.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }
        if(imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("image Url cannot be null or blank");
        }
        if (!isWithinLima(latitude, longitude)) {
            throw new IllegalArgumentException("Coordinates must be within Lima Metropolitana");
        }
    }

    private boolean isWithinLima(float lat, float lng) {
        return lat >= -12.25 && lat <= -11.75 &&
                lng >= -77.20 && lng <= -76.80;
    }
}
