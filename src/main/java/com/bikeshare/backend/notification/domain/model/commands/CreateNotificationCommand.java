package com.bikeshare.backend.notification.domain.model.commands;

import com.bikeshare.backend.notification.domain.model.aggregate.NotificationsType;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record CreateNotificationCommand(Users userId, String message, NotificationsType typeId) {

    public CreateNotificationCommand{
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        if (message == null) {
            throw new IllegalArgumentException("message cannot be null");
        }
        if (typeId == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
    }
}
