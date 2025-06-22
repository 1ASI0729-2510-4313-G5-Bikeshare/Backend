package com.bikeshare.backend.notification.domain.model.aggregate;


import com.bikeshare.backend.notification.domain.model.commands.CreateNotificationCommand;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Users userId;

    @Column(nullable = false)
    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typ_id",nullable = false)
    private NotificationsType typeId;

    @CreatedDate
    private Date created_at;

    protected Notifications() {}

    public Notifications(CreateNotificationCommand command){
        this.userId = command.userId();
        this.message = command.message();
        this.typeId = command.typeId();
    }
}
