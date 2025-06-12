package com.bikeshare.backend.reviewFeedback.domain.model.queries;

public record GetReviewsByReviewerIdAndTargetUserIdQuery(Long reviewerId, Long targetUserId) {
}
