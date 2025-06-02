package com.bikeshare.backend.notification.infrastructure.persistence.jpa;

import com.bikeshare.backend.notification.domain.model.aggregate.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notifications, Integer> {
}
