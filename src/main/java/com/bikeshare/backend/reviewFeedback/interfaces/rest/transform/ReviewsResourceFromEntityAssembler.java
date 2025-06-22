package com.bikeshare.backend.reviewFeedback.interfaces.rest.transform;

import com.bikeshare.backend.reviewFeedback.domain.model.aggregate.Reviews;
import com.bikeshare.backend.reviewFeedback.interfaces.rest.resources.ReviewsResource;

public class ReviewsResourceFromEntityAssembler {
    public static ReviewsResource toResourceFromEntity(Reviews entity) {
        return new ReviewsResource(entity.getReviewerId(), entity.getTargetUserId(),entity.getRating(), entity.getComment());
    }
}
