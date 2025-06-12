package com.bikeshare.backend.bikeInventory.interfaces.rest.transform;

import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikeTypesCommand;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.CreateBikeTypesResource;

public class CreateBikeTypesCommandFromResourceAssembler {

    public static CreateBikeTypesCommand toCommandFromResource(CreateBikeTypesResource resource) {
        return new CreateBikeTypesCommand(resource.typeName());
    }
}
