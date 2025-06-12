package com.bikeshare.backend.bikeInventory.domain.services;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeTypes;
import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikeTypesCommand;

import java.util.Optional;

public interface BikeTypesCommandService {
    Optional<BikeTypes> handle(CreateBikeTypesCommand command);
}
