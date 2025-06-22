package com.bikeshare.backend.reviewFeedback.domain.services;

import com.bikeshare.backend.reviewFeedback.domain.model.aggregate.Reviews;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetAllReviewsQuery;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetReviewsByIdQuery;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetReviewsByTargetUserEmail;

import java.util.List;
import java.util.Optional;

public interface ReviewsQueryService {
    Optional<Reviews> handle(GetReviewsByIdQuery query);

    List<Reviews> handle(GetReviewsByTargetUserEmail query);

    List<Reviews> handle(GetAllReviewsQuery query);
}
