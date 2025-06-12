package com.bikeshare.backend.reviewFeedback.domain.model.aggregate;

import com.bikeshare.backend.reviewFeedback.domain.model.commands.CreateReviewsCommand;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Getter
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="reviewer_id",nullable = false, insertable = true, updatable = true)
    private Users reviewerId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "target_user_id", nullable = false, insertable = true, updatable = true)
    private Users targetUserId;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private String comment;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    protected Reviews() {};

    public Reviews(CreateReviewsCommand command){
        this.reviewerId = command.reviewerId();
        this.targetUserId = command.targetUserId();
        this.rating = command.rating();
        this.comment = command.comment();
    }
}
