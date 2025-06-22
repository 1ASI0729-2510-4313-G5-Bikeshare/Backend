package com.bikeshare.backend.reviewFeedback.interfaces.rest.resources;

import com.bikeshare.backend.userManagement.domain.model.aggregate.UserRoles;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record CreateReviewsResource(Users reviewerId,Users targetUserId, Integer rating, String comment) {
}
