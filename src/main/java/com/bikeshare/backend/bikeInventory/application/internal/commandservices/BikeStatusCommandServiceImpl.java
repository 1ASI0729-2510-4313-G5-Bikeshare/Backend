package com.bikeshare.backend.bikeInventory.application.internal.commandservices;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeStatus;
import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikeStatusCommand;
import com.bikeshare.backend.bikeInventory.domain.services.BikeStatusCommandService;
import com.bikeshare.backend.bikeInventory.infrastructure.persistence.jpa.BikeStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BikeStatusCommandServiceImpl implements BikeStatusCommandService {

    private final BikeStatusRepository bikeStatusRepository;

    BikeStatusCommandServiceImpl(BikeStatusRepository bikeStatusRepository) {
        this.bikeStatusRepository = bikeStatusRepository;
    }

    @Override
    public Optional<BikeStatus> handle(CreateBikeStatusCommand command) {
        if(bikeStatusRepository.existsByStatusName(command.statusName())){
            throw new IllegalArgumentException("Status already exists");
        }

        var bikeStatus = new BikeStatus(command);
        var createdBikeStatus = this.bikeStatusRepository.save(bikeStatus);

        return Optional.of(createdBikeStatus);
    }
}
