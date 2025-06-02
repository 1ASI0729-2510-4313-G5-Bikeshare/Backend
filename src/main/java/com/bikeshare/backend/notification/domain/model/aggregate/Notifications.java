package com.bikeshare.backend.notification.domain.model.aggregate;


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
    private Long notification_id;

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Integer type_id;

    @CreatedDate
    private Date created_at;
}
