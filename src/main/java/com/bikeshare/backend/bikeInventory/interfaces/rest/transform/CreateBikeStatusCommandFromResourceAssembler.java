package com.bikeshare.backend.bikeInventory.interfaces.rest.transform;

import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikeStatusCommand;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.CreateBikeStatusResource;

public class CreateBikeStatusCommandFromResourceAssembler {

    public static CreateBikeStatusCommand toCommandFromResource(CreateBikeStatusResource resource) {
        return new CreateBikeStatusCommand(resource.statusName());
    }
}
