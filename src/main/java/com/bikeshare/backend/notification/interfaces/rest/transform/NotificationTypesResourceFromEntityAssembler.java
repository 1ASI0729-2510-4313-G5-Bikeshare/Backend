package com.bikeshare.backend.notification.interfaces.rest.transform;

import com.bikeshare.backend.notification.domain.model.aggregate.NotificationsType;
import com.bikeshare.backend.notification.interfaces.rest.resources.NotificationTypesResource;

public class NotificationTypesResourceFromEntityAssembler {

    public static NotificationTypesResource toResourceFromEntity(NotificationsType entity) {
        return new NotificationTypesResource(entity.getTypeName());
    }
}
