package com.bikeshare.backend.reviewFeedback.domain.model.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_id;

    @Column(nullable = false)
    private Integer reviewer_id;

    @Column(nullable = false)
    private Integer target_user_id;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    @CreatedDate
    private Date createdAt;
}
