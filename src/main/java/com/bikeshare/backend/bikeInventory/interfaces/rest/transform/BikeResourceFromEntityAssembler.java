package com.bikeshare.backend.bikeInventory.interfaces.rest.transform;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bike;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.BikeResource;

public class BikeResourceFromEntityAssembler {

    public static BikeResource toResourceFromEntity(Bike entity){
        return new BikeResource(entity.getOwnerId(), entity.getModel(), entity.getType(),entity.getStatus(), entity.getLocation(), entity.getImageUrl(), entity.getCostPerMinute());
    }
}
