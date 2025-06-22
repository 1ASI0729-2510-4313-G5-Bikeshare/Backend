package com.bikeshare.backend.bikeInventory.interfaces.rest.transform;

import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikeCommand;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.CreateBikeResource;

public class CreateBikeCommandFromResourceAssembler {

    public static CreateBikeCommand toCommandFromResource(CreateBikeResource resource){
        return new CreateBikeCommand(resource.ownerId(),resource.model(),resource.type(),resource.latitude(), resource.longitude(), resource.imageUrl(),resource.costPerMinute());
    }
}
