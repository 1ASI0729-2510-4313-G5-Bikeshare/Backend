package com.bikeshare.backend.notification.domain.model.queries;

public record GetNotificationByUserIdMessageAndTypeId(Long userId, String message, Long typeId) {
}
