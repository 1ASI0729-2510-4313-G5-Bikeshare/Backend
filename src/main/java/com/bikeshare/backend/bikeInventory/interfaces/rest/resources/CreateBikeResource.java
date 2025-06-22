package com.bikeshare.backend.bikeInventory.interfaces.rest.resources;

import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeTypes;
import java.math.BigDecimal;

public record CreateBikeResource(
        Long ownerId,
        String model,
        BikeTypes type,
        Float latitude,
        Float longitude,
        String imageUrl,
        BigDecimal costPerMinute
) {
    public CreateBikeResource {
        if (ownerId == null || ownerId < 0)
            throw new IllegalArgumentException("ownerId cannot be null or less than 0");
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
