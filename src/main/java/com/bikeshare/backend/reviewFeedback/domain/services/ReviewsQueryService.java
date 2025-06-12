package com.bikeshare.backend.reviewFeedback.domain.services;

import com.bikeshare.backend.reviewFeedback.domain.model.aggregate.Reviews;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetAllReviewsQuery;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetReviewsByIdQuery;
import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetReviewsByReviewerIdAndTargetUserIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ReviewsQueryService {
    Optional<Reviews> handle(GetReviewsByIdQuery query);

    Optional<Reviews> handle(GetReviewsByReviewerIdAndTargetUserIdQuery query);

    List<Reviews> handle(GetAllReviewsQuery query);
}
