package com.bikeshare.backend.reviewFeedback.domain.model.commands;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record CreateReviewsCommand(Users reviewerId, Users targetUserId, Integer rating, String comment) {

    public CreateReviewsCommand{
        if(reviewerId == null){
            throw new IllegalArgumentException("Reviewer user is null or empty");
        }
        if(targetUserId == null){
            throw new IllegalArgumentException("Target user is null or empty");
        }
        if(rating == null || rating < 0){
            throw new IllegalArgumentException("Rating is null or negative");
        }
        if(comment == null || comment.isEmpty()){
            throw new IllegalArgumentException("Comment is null or empty");
        }
    }
}
