package com.bikeshare.backend.notification.interfaces.rest.transform;

import com.bikeshare.backend.notification.domain.model.aggregate.Notifications;
import com.bikeshare.backend.notification.interfaces.rest.resources.NotificationResource;

import javax.management.Notification;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notifications entity) {
        return new NotificationResource(entity.getUserId(), entity.getMessage(), entity.getTypeId());
    }
}
