package com.bikeshare.backend.notification.interfaces.rest.transform;

import com.bikeshare.backend.notification.domain.model.aggregate.NotificationsType;
import com.bikeshare.backend.notification.domain.model.commands.CreateNotificationCommand;
import com.bikeshare.backend.notification.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {

    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(resource.userId(), resource.message(), resource.typeId());
    }
}
