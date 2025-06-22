package com.bikeshare.backend.notification.application.internal.queryservices;

import com.bikeshare.backend.notification.domain.model.aggregate.Notifications;
import com.bikeshare.backend.notification.domain.model.aggregate.NotificationsType;
import com.bikeshare.backend.notification.domain.model.queries.GetAllNotificationQuery;
import com.bikeshare.backend.notification.domain.model.queries.GetNotificationByIdQuery;
import com.bikeshare.backend.notification.domain.model.queries.GetNotificationByUserIdMessageAndTypeId;
import com.bikeshare.backend.notification.domain.services.NotificationQueryService;
import com.bikeshare.backend.notification.infrastructure.persistence.jpa.NotificationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationsRepository notificationsRepository;

    NotificationQueryServiceImpl(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    @Override
    public Optional<Notifications> handle(GetNotificationByIdQuery query) {
        return this.notificationsRepository.findById(Math.toIntExact(query.notificationId()));
    }

    @Override
    public Optional<Notifications> handle(GetNotificationByUserIdMessageAndTypeId query) {
        return this.notificationsRepository.findByUserId_UserIdAndMessageAndTypeId_TypeId(query.userId(), query.message(), query.typeId());
    }

    @Override
    public List<Notifications> handle(GetAllNotificationQuery query) {
        return this.notificationsRepository.findAll();
    }
}
