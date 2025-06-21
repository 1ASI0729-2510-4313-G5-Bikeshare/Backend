package com.bikeshare.backend.ownerManagement.interfaces.rest.resources;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record CreateOwnerProfileResource(
        Users lenderId,
        String bio,
        Double totalEarnings,
        Float rating
) {
    public CreateOwnerProfileResource {
        if (lenderId == null) {
            throw new IllegalArgumentException("Lender ID cannot be null");
        }
        if (bio == null) {
            throw new IllegalArgumentException("Bio cannot be null");
        }
        if (totalEarnings == null) {
            throw new IllegalArgumentException("Total earnings cannot be null");
        }
        if (rating == null) {
            throw new IllegalArgumentException("Rating cannot be null");
        }
    }
}
