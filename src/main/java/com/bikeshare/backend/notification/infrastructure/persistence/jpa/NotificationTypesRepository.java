package com.bikeshare.backend.notification.infrastructure.persistence.jpa;

import com.bikeshare.backend.notification.domain.model.aggregate.NotificationsType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTypesRepository extends JpaRepository<NotificationsType, Long> {

    boolean existsByTypeName(String typeName);
}
