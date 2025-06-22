package com.bikeshare.backend.reviewFeedback.interfaces.rest.transform;

import com.bikeshare.backend.reviewFeedback.domain.model.commands.CreateReviewsCommand;
import com.bikeshare.backend.reviewFeedback.interfaces.rest.resources.CreateReviewsResource;

public class CreateReviewsCommandFromResourceAssembler {
    public static CreateReviewsCommand toCommandFromResource(CreateReviewsResource resource) {
        return new CreateReviewsCommand(resource.reviewerId(), resource.targetUserId(), resource.rating(), resource.comment());
    }
}
