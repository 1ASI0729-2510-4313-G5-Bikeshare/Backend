package com.bikeshare.backend.bikeInventory.interfaces.rest.resources;

import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeStatus;
import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeTypes;

import java.math.BigDecimal;

public record UpdateBikeResource(
        Long requesterId,
        String model,
        BikeTypes type,
        Float latitude,
        Float longitude,
        String imageUrl,
        BikeStatus newStatus,
        BigDecimal costPerMinute
) {
    public UpdateBikeResource {
        if(model == null || model.isBlank() || model.length() < 3)
            throw new IllegalArgumentException("model cannot be null or blank or less than 3");
        if(type == null)
            throw new IllegalArgumentException("type cannot be null");
        if(latitude == null || longitude == null)
            throw new IllegalArgumentException("latitude and longitude cannot be null");
        if(imageUrl == null  || imageUrl.isBlank())
            throw new IllegalArgumentException("imageUrl cannot be null or blank");
        if(costPerMinute == null)
            throw new IllegalArgumentException("newStatus cannot be null");
    }
}
