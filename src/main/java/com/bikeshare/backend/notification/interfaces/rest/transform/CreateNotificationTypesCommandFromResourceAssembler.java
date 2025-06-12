package com.bikeshare.backend.notification.interfaces.rest.transform;

import com.bikeshare.backend.notification.domain.model.commands.CreateNotificationTypeCommand;
import com.bikeshare.backend.notification.interfaces.rest.resources.CreateNotificationTypeResource;
import com.bikeshare.backend.userManagement.domain.model.commands.CreateUserRolesCommand;
import com.bikeshare.backend.userManagement.interfaces.rest.resources.CreateUserRolesResource;
import com.bikeshare.backend.userManagement.interfaces.rest.transform.CreateUserRolesCommandFromResourceAssembler;

public class CreateNotificationTypesCommandFromResourceAssembler {

    public static CreateNotificationTypeCommand toCommandFromResource(CreateNotificationTypeResource resource) {
        return new CreateNotificationTypeCommand(resource.typeName());
    }
}
