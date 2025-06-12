package com.bikeshare.backend.lenderManagement.domain.services;

import com.bikeshare.backend.lenderManagement.domain.model.aggregate.LenderProfiles;
import com.bikeshare.backend.lenderManagement.domain.model.commands.CreateLenderProfileCommand;

import java.util.Optional;

public interface LenderProfileCommandService {

    Optional<LenderProfiles> handle(CreateLenderProfileCommand command);
}
