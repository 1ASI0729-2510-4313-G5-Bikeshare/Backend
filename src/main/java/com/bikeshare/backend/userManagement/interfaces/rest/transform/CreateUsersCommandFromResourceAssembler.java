package com.bikeshare.backend.userManagement.interfaces.rest.transform;

import com.bikeshare.backend.userManagement.domain.model.commands.CreateUsersCommand;
import com.bikeshare.backend.userManagement.interfaces.rest.resources.CreateUsersResource;

public class CreateUsersCommandFromResourceAssembler {
    public static CreateUsersCommand toCommandFromResource(CreateUsersResource resource) {
        return new CreateUsersCommand(resource.email(), resource.password_hash(), resource.full_name(), resource.role());
    }
}
