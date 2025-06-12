package com.bikeshare.backend.bikeInventory.interfaces.rest.resources;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeStatus;
import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeTypes;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import org.apache.catalina.User;

public record BikeResource(
        Users ownerId,
        String model,
        BikeTypes typeId,
        BikeStatus statusId,
        Double latitude,
        Double longitude
) {
}
