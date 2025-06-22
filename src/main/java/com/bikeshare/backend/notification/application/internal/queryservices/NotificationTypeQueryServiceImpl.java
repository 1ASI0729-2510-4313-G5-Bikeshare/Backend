package com.bikeshare.backend.notification.application.internal.queryservices;


import com.bikeshare.backend.notification.domain.model.aggregate.NotificationsType;
import com.bikeshare.backend.notification.domain.model.queries.GetAllNotificationTypesQuery;
import com.bikeshare.backend.notification.domain.model.queries.GetNotificationTypeByIdQuery;
import com.bikeshare.backend.notification.domain.services.NotificationTypesQueryService;
import com.bikeshare.backend.notification.infrastructure.persistence.jpa.NotificationTypesRepository;
import com.bikeshare.backend.userManagement.domain.services.UserRolesQueryService;
import com.bikeshare.backend.userManagement.infrastructure.persistance.jpa.UserRolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationTypeQueryServiceImpl implements NotificationTypesQueryService {

    private final NotificationTypesRepository notificationTypesRepository;

    NotificationTypeQueryServiceImpl(NotificationTypesRepository notificationTypesRepository) {
        this.notificationTypesRepository = notificationTypesRepository;
    }

    @Override
    public Optional<NotificationsType> handle(GetNotificationTypeByIdQuery query){
        return this.notificationTypesRepository.findById(query.typeId());
    }

    @Override
    public List<NotificationsType> handle(GetAllNotificationTypesQuery query){
        return this.notificationTypesRepository.findAll();
    }
}
