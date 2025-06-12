package com.bikeshare.backend.rentalOperations.interfaces.rest.transform;

import com.bikeshare.backend.rentalOperations.domain.model.commands.CreateRentalCommand;
import com.bikeshare.backend.rentalOperations.interfaces.rest.resources.CreateRentalResource;
import com.bikeshare.backend.rentalOperations.interfaces.rest.resources.RentalResource;

public class CreateRentalCommandFromResourceAssembler {

    public static CreateRentalCommand toCommandFromResource(CreateRentalResource resource) {
        return new CreateRentalCommand(
                resource.bikeId(),
                resource.clientId(),
                resource.startTime(),
                resource.endTime(),
                resource.statusId(),
                resource.price()
        );
    }
}
