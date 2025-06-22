package com.bikeshare.backend.notification.interfaces.rest.resources;

import com.bikeshare.backend.notification.domain.model.aggregate.Notifications;
import com.bikeshare.backend.notification.domain.model.aggregate.NotificationsType;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record NotificationResource(Users userId, String message, NotificationsType typeId) {
}
