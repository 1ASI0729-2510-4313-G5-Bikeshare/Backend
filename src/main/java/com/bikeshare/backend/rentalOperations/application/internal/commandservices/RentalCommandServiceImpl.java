package com.bikeshare.backend.rentalOperations.application.internal.commandservices;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.Rentals;
import com.bikeshare.backend.rentalOperations.domain.model.commands.CreateRentalCommand;
import com.bikeshare.backend.rentalOperations.domain.services.RentalCommandService;
import com.bikeshare.backend.rentalOperations.infrastructure.persistence.jpa.RentalsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RentalCommandServiceImpl implements RentalCommandService {

    private final RentalsRepository rentalsRepository;

    RentalCommandServiceImpl(RentalsRepository rentalsRepository) {
        this.rentalsRepository = rentalsRepository;
    }

    @Override
    public Optional<Rentals> handle(CreateRentalCommand command) {
        var rental = new Rentals(command);
        if(rentalsRepository.existsByBikeId_BikeIdAndClientId_UserIdAndStartTime(command.bikeId().getBikeId(), command.clientId().getUserId(), command.startTime())){
            throw new IllegalArgumentException("bikeId and UserId and startTime cannot be the same");
        }

        rentalsRepository.save(rental);
        return Optional.of(rental);

    }

}
