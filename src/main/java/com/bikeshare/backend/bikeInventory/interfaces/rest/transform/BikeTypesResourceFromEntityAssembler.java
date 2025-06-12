package com.bikeshare.backend.bikeInventory.interfaces.rest.transform;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeTypes;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.BikeTypesResource;

public class BikeTypesResourceFromEntityAssembler {
    public static BikeTypesResource toResourceFromEntity(BikeTypes entity) {
        return new BikeTypesResource(entity.getTypeName());
    }
}
