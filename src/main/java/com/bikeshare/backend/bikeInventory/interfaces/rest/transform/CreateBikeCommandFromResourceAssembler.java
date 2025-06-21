package com.bikeshare.backend.bikeInventory.interfaces.rest.transform;

import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikesCommand;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.CreateBikeResource;

public class CreateBikeCommandFromResourceAssembler {

    public static CreateBikesCommand toCommandFromResource(CreateBikeResource resource){
        return new CreateBikesCommand(resource.ownerId(),resource.model(),resource.typeId(),resource.statusId(),resource.latitude(),resource.imageUrl(),resource.costPerMinute(),resource.longitude());
    }
}
