package com.bikeshare.backend.rentalOperations.interfaces.rest.transform;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.Rentals;
import com.bikeshare.backend.rentalOperations.interfaces.rest.resources.RentalResource;

public class RentalResourceFromEntityAssembler {

    public static RentalResource toResourceFromEntity(Rentals entity){
        return new RentalResource(
                entity.getBikeId(),
                entity.getClientId(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getStatusId(),
                entity.getPrice()
        );
    }
}
