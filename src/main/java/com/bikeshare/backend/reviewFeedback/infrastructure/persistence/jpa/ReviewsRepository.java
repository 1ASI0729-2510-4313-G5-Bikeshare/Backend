package com.bikeshare.backend.reviewFeedback.infrastructure.persistence.jpa;

import com.bikeshare.backend.reviewFeedback.domain.model.aggregate.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
}
