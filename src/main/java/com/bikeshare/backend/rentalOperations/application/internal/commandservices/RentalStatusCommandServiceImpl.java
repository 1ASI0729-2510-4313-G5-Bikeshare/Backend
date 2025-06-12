package com.bikeshare.backend.rentalOperations.application.internal.commandservices;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.RentalStatus;
import com.bikeshare.backend.rentalOperations.domain.model.commands.CreateRentalStatusCommand;
import com.bikeshare.backend.rentalOperations.domain.services.RentalStatusCommandService;
import com.bikeshare.backend.rentalOperations.infrastructure.persistence.jpa.RentalStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalStatusCommandServiceImpl implements RentalStatusCommandService {

    private final RentalStatusRepository rentalStatusRepository;

    RentalStatusCommandServiceImpl(RentalStatusRepository rentalStatusRepository) {
        this.rentalStatusRepository = rentalStatusRepository;
    }

    @Override
    public Optional<RentalStatus> handle(CreateRentalStatusCommand command) {
        var rentalStatus = new RentalStatus(command);

        if(rentalStatusRepository.existsByStatusName(command.statusName())) {
            throw new IllegalArgumentException("Rental Status already exists");
        }

        rentalStatusRepository.save(rentalStatus);
        return Optional.of(rentalStatus);
    }
}
