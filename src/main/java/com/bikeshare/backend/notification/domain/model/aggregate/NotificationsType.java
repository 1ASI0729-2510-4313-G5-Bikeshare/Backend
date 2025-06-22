package com.bikeshare.backend.notification.domain.model.aggregate;

import com.bikeshare.backend.notification.domain.model.commands.CreateNotificationTypeCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
public class NotificationsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    @Column(nullable = false)
    private String typeName;

    @CreatedDate
    private Date created_at;

    protected NotificationsType() {}

    public NotificationsType(CreateNotificationTypeCommand command) {
        this.typeName = command.typeName();
    }
}
