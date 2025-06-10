package com.bikeshare.backend.reviewFeedback.infrastructure.persistence.jpa;

import com.bikeshare.backend.reviewFeedback.domain.model.aggregate.Reviews;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    //Used for GETS
    Optional<Reviews> findByReviewerId_UserIdAndTargetUserId_UserId(Long reviewerId, Long targetUserId);

    //To verify inside the code itself
    boolean existsByReviewerId_UserIdAndTargetUserId_UserId(Long reviewerId, Long targetUserId);

    boolean existsByReviewId(Long reviewId);
}
