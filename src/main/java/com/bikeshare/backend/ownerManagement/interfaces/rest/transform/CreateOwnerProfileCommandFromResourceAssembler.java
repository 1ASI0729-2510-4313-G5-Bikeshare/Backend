package com.bikeshare.backend.ownerManagement.interfaces.rest.transform;

import com.bikeshare.backend.ownerManagement.domain.model.commands.CreateOwnerProfileCommand;
import com.bikeshare.backend.ownerManagement.interfaces.rest.resources.CreateOwnerProfileResource;

public class CreateOwnerProfileCommandFromResourceAssembler {

    public static CreateOwnerProfileCommand toCommandFromResource(CreateOwnerProfileResource resource) {
        return new CreateOwnerProfileCommand(
                resource.lenderId(),
                resource.bio(),
                resource.totalEarnings(),
                resource.rating()
        );
    }
}
