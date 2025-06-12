package com.bikeshare.backend.rentalOperations.interfaces.rest.transform;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.RentalStatus;
import com.bikeshare.backend.rentalOperations.interfaces.rest.resources.RentalStatusResource;

public class RentalStatusResourceFromEntityAssembler {

    public static RentalStatusResource toResourceFromEntity(RentalStatus entity) {
        return new RentalStatusResource(entity.getStatusName());
    }
}
