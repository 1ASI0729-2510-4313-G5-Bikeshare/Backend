package com.bikeshare.backend.rentalOperations.domain.services;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.RentalStatus;
import com.bikeshare.backend.rentalOperations.domain.model.commands.CreateRentalStatusCommand;

import java.util.Optional;

public interface RentalStatusCommandService {

    Optional<RentalStatus> handle(CreateRentalStatusCommand command);
}
