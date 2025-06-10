package com.bikeshare.backend.reviewFeedback.application.internal.queryservices;

import com.bikeshare.backend.reviewFeedback.domain.model.aggregate.Reviews;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetAllReviewsQuery;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetReviewsByIdQuery;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetReviewsByReviewerIdAndTargetUserId;
import com.bikeshare.backend.reviewFeedback.domain.services.ReviewsQueryService;
import com.bikeshare.backend.reviewFeedback.infrastructure.persistence.jpa.ReviewsRepository;
import com.bikeshare.backend.userManagement.domain.model.queries.GetUsersByIdQuery;

import java.util.Optional;

public class ReviewsQueryServiceImpl implements ReviewsQueryService {
    private ReviewsRepository reviewsRepository;

    ReviewsQueryServiceImpl(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    @Override
    public Optional<Reviews> handle(GetReviewsByIdQuery query){
        return this.reviewsRepository.findById(query.reviewId());
    }

    @Override
    public Optional<Reviews> handle(GetReviewsByReviewerIdAndTargetUserId query){
        return this.reviewsRepository.findByReviewerId_UserIdAndTargetUserId_UserId(query.reviewerId(), query.targetUserId());
    }

    public Optional<Reviews> handle(GetAllReviewsQuery query){
        return this.reviewsRepository
    }
}
