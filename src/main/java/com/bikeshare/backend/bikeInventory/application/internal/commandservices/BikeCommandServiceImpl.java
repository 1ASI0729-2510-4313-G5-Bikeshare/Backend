package com.bikeshare.backend.bikeInventory.application.internal.commandservices;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bikes;
import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikesCommand;
import com.bikeshare.backend.bikeInventory.domain.services.BikeCommandService;
import com.bikeshare.backend.bikeInventory.infrastructure.persistence.jpa.BikesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BikeCommandServiceImpl implements BikeCommandService {
    private final BikesRepository bikesRepository;

    BikeCommandServiceImpl(BikesRepository bikesRepository) {
        this.bikesRepository = bikesRepository;
    }

    @Override
    public Optional<Bikes> handle(CreateBikesCommand command) {
        var bikes = new Bikes(command);
        bikesRepository.save(bikes);

        return Optional.of(bikes);
    }
}
