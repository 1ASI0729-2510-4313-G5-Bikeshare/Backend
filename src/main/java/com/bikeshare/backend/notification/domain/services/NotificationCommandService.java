package com.bikeshare.backend.notification.domain.services;

import com.bikeshare.backend.notification.domain.model.aggregate.Notifications;
import com.bikeshare.backend.notification.domain.model.aggregate.NotificationsType;
import com.bikeshare.backend.notification.domain.model.commands.CreateNotificationCommand;
import com.bikeshare.backend.notification.domain.model.commands.CreateNotificationTypeCommand;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.util.Optional;


public interface NotificationCommandService {
    Optional<Notifications> handle(CreateNotificationCommand command);
}
