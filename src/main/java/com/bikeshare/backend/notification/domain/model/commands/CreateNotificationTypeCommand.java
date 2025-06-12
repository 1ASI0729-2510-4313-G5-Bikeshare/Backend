package com.bikeshare.backend.notification.domain.model.commands;

public record CreateNotificationTypeCommand(String typeName) {

    public CreateNotificationTypeCommand{
        if (typeName == null || typeName.isEmpty()){
            throw new IllegalArgumentException("typeName cannot be null or empty");
        }
    }
}
