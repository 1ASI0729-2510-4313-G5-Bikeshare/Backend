package com.bikeshare.backend.bikeInventory.interfaces.rest.transform;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bikes;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.BikeResource;

public class BikeResourceFromEntityAssembler {

    public static BikeResource toResourceFromEntity(Bikes entity){
        return new BikeResource(entity.getOwnerId(), entity.getModel(), entity.getTypeId(),entity.getStatusId(), entity.getLatitude(), entity.getLongitude());
    }
}
