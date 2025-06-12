package com.bikeshare.backend.lenderManagement.interfaces.rest.resources;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record LenderProfileResource(
        Users lenderId,
        String bio,
        Double totalEarnings,
        Float rating
) {
}
