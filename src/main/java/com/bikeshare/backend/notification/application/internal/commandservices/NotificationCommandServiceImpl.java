package com.bikeshare.backend.notification.application.internal.commandservices;

import com.bikeshare.backend.notification.domain.model.aggregate.Notifications;
import com.bikeshare.backend.notification.domain.model.aggregate.NotificationsType;
import com.bikeshare.backend.notification.domain.model.commands.CreateNotificationCommand;
import com.bikeshare.backend.notification.infrastructure.persistence.jpa.NotificationsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements com.bikeshare.backend.notification.domain.services.NotificationCommandService {

    private final NotificationsRepository notificationsRepository;

    NotificationCommandServiceImpl(NotificationsRepository notificationsRepository) { this.notificationsRepository = notificationsRepository; }

    @Override
    public Optional<Notifications> handle(CreateNotificationCommand command) {
        if(notificationsRepository.existsByUserId_UserIdAndMessageAndTypeId_TypeId(command.userId().getUserId(), command.message(), command.typeId().getTypeId())){
            throw new IllegalArgumentException("User with id " + command.userId().getUserId() + " already exists");
        }
        var notifications = new Notifications(command);
        var createdNotifications = notificationsRepository.save(notifications);

        return Optional.of(createdNotifications);
    }
}
