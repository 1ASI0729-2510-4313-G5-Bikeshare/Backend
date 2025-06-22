package com.bikeshare.backend.bikeInventory.interfaces.rest.resources;

import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.*;


public record BikeResource(
        OwnerId ownerId,
        String model,
        BikeTypes type,
        BikeStatus status,
        Coordinates location,
        ImageUrl imageUrl,
        Money costPerMinute
) {

}
