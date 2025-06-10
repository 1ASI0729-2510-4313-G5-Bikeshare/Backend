package com.bikeshare.backend.reviewFeedback.application.internal.commandservices;

import com.bikeshare.backend.reviewFeedback.domain.model.aggregate.Reviews;
import com.bikeshare.backend.reviewFeedback.domain.model.commands.CreateReviewsCommand;
import com.bikeshare.backend.reviewFeedback.domain.services.ReviewsCommandService;
import com.bikeshare.backend.reviewFeedback.infrastructure.persistence.jpa.ReviewsRepository;
import com.bikeshare.backend.reviewFeedback.interfaces.rest.resources.ReviewsResource;

import java.util.Optional;

public class ReviewsCommandServiceImpl implements ReviewsCommandService {
  private final ReviewsRepository reviewsRepository;

  ReviewsCommandServiceImpl(ReviewsRepository reviewsRepository) {
      this.reviewsRepository = reviewsRepository;
  }
  @Override
    public Optional<Reviews> handle(CreateReviewsCommand command) {
      if(reviewsRepository.existsByReviewerId_UserIdAndTargetUserId_UserId(command.reviewerId().getUserId(), command.targetUserId().getUserId())){
          throw new IllegalArgumentException("Reviewer already exists");
      }
      var reviews = new Reviews(command);
      var createdReviews = reviewsRepository.save(reviews);

      return Optional.of(createdReviews);
  }
}
