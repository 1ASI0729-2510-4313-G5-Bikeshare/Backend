package com.bikeshare.backend.bikeInventory.domain.services;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bikes;
import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikesCommand;

import java.util.Optional;

public interface BikeCommandService {

    Optional<Bikes> handle(CreateBikesCommand command);
}
