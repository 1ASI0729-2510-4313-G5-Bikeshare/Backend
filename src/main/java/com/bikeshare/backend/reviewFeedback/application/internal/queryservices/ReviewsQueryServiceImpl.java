package com.bikeshare.backend.reviewFeedback.application.internal.queryservices;

import com.bikeshare.backend.reviewFeedback.domain.model.aggregate.Reviews;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetAllReviewsQuery;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetReviewsByIdQuery;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetReviewsByTargetUserEmail;
import com.bikeshare.backend.reviewFeedback.domain.services.ReviewsQueryService;
import com.bikeshare.backend.reviewFeedback.infrastructure.persistence.jpa.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsQueryServiceImpl implements ReviewsQueryService {

    private final ReviewsRepository reviewsRepository;

    ReviewsQueryServiceImpl(ReviewsRepository reviewsRepository) {

        this.reviewsRepository = reviewsRepository;
    }

    @Override
    public Optional<Reviews> handle(GetReviewsByIdQuery query){
        return this.reviewsRepository.findById(query.reviewId());
    }

    @Override
    public List<Reviews> handle(GetReviewsByTargetUserEmail query){
        return this.reviewsRepository.findByTargetUserId_Email(query.email());
    }

    @Override
    public List<Reviews> handle(GetAllReviewsQuery query){

        return this.reviewsRepository.findAll();
    }
}
