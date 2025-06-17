package com.bikeshare.backend.reviewFeedback.infrastructure.persistence.jpa;

import com.bikeshare.backend.reviewFeedback.domain.model.aggregate.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    //Used for GETS
    List<Reviews> findByTargetUserId_Email(String email);
    List<Reviews> findByReviewerId_UserId(Long reviewId);

    //To verify inside the code itself
    boolean existsByReviewerId_UserIdAndTargetUserId_UserId(Long reviewerId, Long targetUserId);

    boolean existsByReviewId(Long reviewId);
}
