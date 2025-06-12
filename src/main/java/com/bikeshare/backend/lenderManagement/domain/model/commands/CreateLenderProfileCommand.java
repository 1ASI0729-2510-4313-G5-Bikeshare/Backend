package com.bikeshare.backend.lenderManagement.domain.model.commands;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record CreateLenderProfileCommand(
        Users lenderId,
        String bio,
        Double totalEarnings,
        Float rating
) {
}
