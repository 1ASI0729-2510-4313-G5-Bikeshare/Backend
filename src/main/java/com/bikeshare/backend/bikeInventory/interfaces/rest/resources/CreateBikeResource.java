package com.bikeshare.backend.bikeInventory.interfaces.rest.resources;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeStatus;
import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeTypes;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record CreateBikeResource(
        Users ownerId,
        String model,
        BikeTypes typeId,
        BikeStatus statusId,
        Double latitude,
        Double longitude, String imageUrl, Double costPerMinute
)
{
    public CreateBikeResource{
        if(ownerId == null)
            throw new IllegalArgumentException("ownerId cannot be null");
        if(model == null || model.isEmpty())
            throw new IllegalArgumentException("model cannot be null or empty");
        if(typeId == null)
            throw new IllegalArgumentException("typeId cannot be null");
        if(statusId == null)
            throw new IllegalArgumentException("statusId cannot be null");
        if(latitude == null || longitude == null)
            throw new IllegalArgumentException("latitude and longitude cannot be null");
        if(imageUrl == null)
            throw new IllegalArgumentException("statusId cannot be null");
        if(costPerMinute == null)
            throw new IllegalArgumentException("statusId cannot be null");
    }

}
