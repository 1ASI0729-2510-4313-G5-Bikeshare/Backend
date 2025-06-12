package com.bikeshare.backend.rentalOperations.interfaces.rest.transform;

import com.bikeshare.backend.rentalOperations.domain.model.commands.CreateRentalStatusCommand;
import com.bikeshare.backend.rentalOperations.interfaces.rest.resources.CreateRentalStatusResource;

public class CreateRentalStatusCommandFromResourceAssembler {

    public static CreateRentalStatusCommand toCommandFromResource(CreateRentalStatusResource resource) {
        return new CreateRentalStatusCommand(resource.statusName());
    }
}
