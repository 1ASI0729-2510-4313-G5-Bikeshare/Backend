package com.bikeshare.backend.reviewFeedback.domain.services;

import com.bikeshare.backend.reviewFeedback.domain.model.aggregate.Reviews;
import com.bikeshare.backend.reviewFeedback.domain.model.commands.CreateReviewsCommand;

import java.util.Optional;

public interface ReviewsCommandService {
    Optional<Reviews> handle(CreateReviewsCommand command);
}
