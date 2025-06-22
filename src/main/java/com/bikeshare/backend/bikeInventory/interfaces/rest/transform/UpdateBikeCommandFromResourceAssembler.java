package com.bikeshare.backend.bikeInventory.interfaces.rest.transform;

import com.bikeshare.backend.bikeInventory.domain.model.commands.UpdateBikeCommand;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.UpdateBikeResource;

public class UpdateBikeCommandFromResourceAssembler {
    public static UpdateBikeCommand toCommandFromResource(Long id, UpdateBikeResource resource) {
        return new UpdateBikeCommand(id, resource.requesterId(), resource.model(),resource.type(), resource.newStatus(), resource.latitude(), resource.longitude(), resource.imageUrl(), resource.costPerMinute());
    }
}
