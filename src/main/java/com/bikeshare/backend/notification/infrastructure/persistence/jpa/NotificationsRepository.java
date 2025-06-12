package com.bikeshare.backend.notification.infrastructure.persistence.jpa;

import com.bikeshare.backend.notification.domain.model.aggregate.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Integer> {

    Optional<Notifications> findByUserId_UserIdAndMessageAndTypeId_TypeId(Long userId, String message, Long typeId);

    boolean existsByUserId_UserIdAndMessageAndTypeId_TypeId(Long userId, String message, Long typeId);

    boolean existsByNotificationId(Long notificationId);
}
