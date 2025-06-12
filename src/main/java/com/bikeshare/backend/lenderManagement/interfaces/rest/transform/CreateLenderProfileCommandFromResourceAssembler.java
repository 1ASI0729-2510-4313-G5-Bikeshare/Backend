package com.bikeshare.backend.lenderManagement.interfaces.rest.transform;

import com.bikeshare.backend.lenderManagement.domain.model.commands.CreateLenderProfileCommand;
import com.bikeshare.backend.lenderManagement.interfaces.rest.resources.CreateLenderProfileResource;

public class CreateLenderProfileCommandFromResourceAssembler {

    public static CreateLenderProfileCommand toCommandFromResource(CreateLenderProfileResource resource) {
        return new CreateLenderProfileCommand(
                resource.lenderId(),
                resource.bio(),
                resource.totalEarnings(),
                resource.rating()
        );
    }
}
