package com.bikeshare.backend.bikeInventory.domain.model.commands;

import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeStatus;
import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeTypes;

import java.math.BigDecimal;

/**
 * Command to update a bike.
 *
 * @param id                The ID of the bike to update.
 *                          Must not be null or less than or equal to 0.
 * @param requesterId       The ID of the user requesting the update.
 *                          Must not be null or less than or equal to 0.
 * @param model             The model of the bike.
 *                          Must not be null, blank, or less than 3 characters.
 * @param type              The type of the bike.
 *                          Must not be null.
 * @param newStatus         The new status of the bike.
 *                          Must not be null.
 * @param latitude          The new latitude of the bike's location.
 *                          Must be between -90 and 90 and within Lima Metropolitana.
 * @param longitude         The new longitude of the bike's location.
 *                          Must be between -180 and 180 and within Lima Metropolitana.
 * @param imageUrl          The image URL of the bike.
 *                          Must not be null or blank.
 * @param newCostPerMinute  The new cost per minute of the bike.
 *                          Must not be null or less than or equal to 0.
 */
public record UpdateBikeCommand(Long id,
                                Long requesterId,
                                String model,
                                BikeTypes type,
                                BikeStatus newStatus,
                                Float latitude,
                                Float longitude,
                                String imageUrl,
                                BigDecimal newCostPerMinute

) {
    /**
     * Constructor for {@link UpdateBikeCommand}.
     *
     * @throws IllegalArgumentException if any parameter is invalid:
     *    id or requesterId is null or less than or equal to 0
     *    model is null, blank, or has less than 3 characters
     *    type or newStatus is null
     *    latitude or longitude is outside valid range or not in Lima
     *    imageUrl is null or blank
     *    newCostPerMinute is null or not positive
     */
    public UpdateBikeCommand {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Bike ID cannot be null or less than or equal to 0");
        }
        if (requesterId == null || requesterId <= 0) {
            throw new IllegalArgumentException("requester ID cannot be null or less than or equal to 0");
        }
        if(model == null || model.isBlank() || model.length() < 3) {
            throw new IllegalArgumentException("model cannot be null or  less than 3 characters or blank");
        }
        if(type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        if(newStatus == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        if(imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("image Url cannot be null or blank");
        }
        if (newCostPerMinute == null || newCostPerMinute.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }
        if (!isWithinLima(latitude, longitude)) {
            throw new IllegalArgumentException("New location must be within Lima");
        }
    }

    private boolean isWithinLima(float lat, float lng) {
        return lat >= -12.25 && lat <= -11.75 &&
                lng >= -77.20 && lng <= -76.80;
    }
}
