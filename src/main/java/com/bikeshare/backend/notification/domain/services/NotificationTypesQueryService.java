package com.bikeshare.backend.notification.domain.services;

import com.bikeshare.backend.notification.domain.model.aggregate.NotificationsType;
import com.bikeshare.backend.notification.domain.model.queries.GetAllNotificationTypesQuery;
import com.bikeshare.backend.notification.domain.model.queries.GetNotificationTypeByIdQuery;

import java.util.List;
import java.util.Optional;

public interface NotificationTypesQueryService {
    Optional<NotificationsType> handle(GetNotificationTypeByIdQuery query);
    List<NotificationsType> handle(GetAllNotificationTypesQuery query);
}
