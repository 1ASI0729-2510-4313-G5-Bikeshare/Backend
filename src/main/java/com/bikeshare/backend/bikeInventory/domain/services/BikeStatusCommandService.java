package com.bikeshare.backend.bikeInventory.domain.services;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeStatus;
import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikeStatusCommand;

import java.util.Optional;

public interface BikeStatusCommandService {
    Optional<BikeStatus> handle(CreateBikeStatusCommand command);
}
