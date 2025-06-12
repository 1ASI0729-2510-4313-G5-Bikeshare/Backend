package com.bikeshare.backend.notification.application.internal.commandservices;

import com.bikeshare.backend.notification.domain.model.aggregate.NotificationsType;
import com.bikeshare.backend.notification.domain.model.commands.CreateNotificationTypeCommand;
import com.bikeshare.backend.notification.domain.services.NotificationTypesCommandService;
import com.bikeshare.backend.notification.infrastructure.persistence.jpa.NotificationTypesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationTypesCommandServiceImpl implements NotificationTypesCommandService {

    private final NotificationTypesRepository notificationTypesRepository;

    NotificationTypesCommandServiceImpl(NotificationTypesRepository notificationTypesRepository) {
        this.notificationTypesRepository = notificationTypesRepository;
    }

    @Override
    public Optional<NotificationsType> handle(CreateNotificationTypeCommand command){
        if(notificationTypesRepository.existsByTypeName(command.typeName())){
            throw new IllegalArgumentException("Type name already exists");
        }
        var notificationType = new NotificationsType(command);
        var createdNotificationType = notificationTypesRepository.save(notificationType);

        return Optional.of(createdNotificationType);
    }

}
