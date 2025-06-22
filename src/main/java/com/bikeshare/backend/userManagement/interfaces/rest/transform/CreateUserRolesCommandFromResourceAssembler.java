package com.bikeshare.backend.userManagement.interfaces.rest.transform;

import com.bikeshare.backend.userManagement.domain.model.commands.CreateUserRolesCommand;
import com.bikeshare.backend.userManagement.interfaces.rest.resources.CreateUserRolesResource;

public class CreateUserRolesCommandFromResourceAssembler {

    public static CreateUserRolesCommand toCommandFromResource(CreateUserRolesResource resource) {
        return new CreateUserRolesCommand(resource.role_name());
    }
}
