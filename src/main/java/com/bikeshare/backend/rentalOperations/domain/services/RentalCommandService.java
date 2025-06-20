package com.bikeshare.backend.rentalOperations.domain.services;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.Rentals;
import com.bikeshare.backend.rentalOperations.domain.model.commands.CreateRentalCommand;

import java.util.Optional;

public interface RentalCommandService {

    boolean deleteRental(Long rentalId);

    Optional<Rentals> handle(CreateRentalCommand command);
}
