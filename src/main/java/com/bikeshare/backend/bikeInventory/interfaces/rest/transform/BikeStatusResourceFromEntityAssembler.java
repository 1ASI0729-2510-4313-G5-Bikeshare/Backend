package com.bikeshare.backend.bikeInventory.interfaces.rest.transform;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeStatus;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.BikeStatusResource;

public class BikeStatusResourceFromEntityAssembler {
    public static BikeStatusResource toResourceFromEntity(BikeStatus entity) {
        return new BikeStatusResource(entity.getStatusName());
    }
}
