package com.bikeshare.backend.reviewFeedback.domain.model.queries;

public record GetReviewsByReviewerIdAndTargetUserId(Long reviewerId, Long targetUserId) {
}
