package com.bikeshare.backend.ownerManagement.domain.model.commands;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record CreateOwnerProfileCommand(
        Users ownerId,
        String bio,
        Double totalEarnings,
        Float rating
) {
}
