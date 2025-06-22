package com.bikeshare.backend.reviewFeedback.interfaces.rest.resources;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record ReviewsResource(Users reviewerId, Users targetUserId, Integer rating, String comment) {
}
