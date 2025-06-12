package com.bikeshare.backend.notification.domain.services;

import com.bikeshare.backend.notification.domain.model.aggregate.Notifications;
import com.bikeshare.backend.notification.domain.model.queries.*;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.util.List;
import java.util.Optional;


public interface NotificationQueryService {
    Optional<Notifications> handle(GetNotificationByIdQuery query);
    List<Notifications> handle(GetAllNotificationQuery query);
    Optional<Notifications> handle(GetNotificationByUserIdMessageAndTypeId query);
}
