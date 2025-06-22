package com.bikeshare.backend.ownerManagement.interfaces.rest.resources;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record OwnerProfileResource(
        Users lenderId,
        String bio,
        Double totalEarnings,
        Float rating
) {
}
