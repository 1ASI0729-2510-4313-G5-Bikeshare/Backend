package com.bikeshare.backend.notification.domain.services;

import com.bikeshare.backend.notification.domain.model.aggregate.NotificationsType;
import com.bikeshare.backend.notification.domain.model.commands.CreateNotificationTypeCommand;

import java.util.Optional;

public interface NotificationTypesCommandService {
    Optional<NotificationsType> handle(CreateNotificationTypeCommand command);
}
