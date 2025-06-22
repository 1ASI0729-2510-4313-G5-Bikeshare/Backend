package com.bikeshare.backend.bikeInventory.application.internal.commandservices;

import com.bikeshare.backend.bikeInventory.domain.exceptions.BikeNotFoundException;
import com.bikeshare.backend.bikeInventory.domain.exceptions.BikePersistenceException;
import com.bikeshare.backend.bikeInventory.domain.exceptions.DuplicateBikeModelAndTypeException;
import com.bikeshare.backend.bikeInventory.domain.exceptions.UnauthorizedBikeActionException;
import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bike;
import com.bikeshare.backend.bikeInventory.domain.model.commands.*;
import com.bikeshare.backend.bikeInventory.domain.services.BikeCommandService;
import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.OwnerId;
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
    public Long handle(CreateBikeCommand command) {
        if (bikesRepository.existsByModelAndType(command.model(), command.type()))
            throw new DuplicateBikeModelAndTypeException(command.model(), command.type());
        var bike = new Bike(command);
        try {
            bikesRepository.save(bike);
        } catch (Exception e) {
            throw new BikePersistenceException("Error saving bike: %s".formatted(e.getMessage()));
        }
        return bike.getId();
    }

    private Bike getOwnedBikeOrThrow(Long id, Long requesterId) {
        var bike = bikesRepository.findById(id)
                .orElseThrow(() -> new BikeNotFoundException(id));

        if (!bike.getOwnerId().equals(new OwnerId(requesterId))) {
            throw new UnauthorizedBikeActionException(requesterId);
        }
        return bike;
    }

    @Override
    public Optional<Bike> handle(UpdateBikeCommand command) {
        if (bikesRepository.existsByModelAndTypeAndIdNot(command.model(), command.type(), command.id()))
            throw new DuplicateBikeModelAndTypeException(command.model(), command.type());
        var bikeToUpdate = getOwnedBikeOrThrow(command.id(), command.requesterId());
        try {
            var updatedBike = bikesRepository.save(bikeToUpdate.updateBike(command.model(), command.type(), command.newStatus(), command.latitude(), command.longitude(), command.imageUrl(), command.newCostPerMinute()));
            return Optional.of(updatedBike);
        }  catch (Exception e) {
            throw new BikePersistenceException("Error updating bike: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(DeleteBikeCommand command) {
        var bikeToDelete = getOwnedBikeOrThrow(command.id(), command.requesterId());
        try {
            bikesRepository.delete(bikeToDelete);
        } catch (Exception e) {
            throw new BikePersistenceException("Error deleting bike: %s".formatted(e.getMessage()));
        }
    }

}

