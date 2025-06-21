package com.bikeshare.backend.ownerManagement.domain.services;

import com.bikeshare.backend.ownerManagement.domain.model.aggregate.OwnerProfiles;
import com.bikeshare.backend.ownerManagement.domain.model.commands.CreateOwnerProfileCommand;

import java.util.Optional;

public interface OwnerProfileCommandService {

    Optional<OwnerProfiles> handle(CreateOwnerProfileCommand command);
}
