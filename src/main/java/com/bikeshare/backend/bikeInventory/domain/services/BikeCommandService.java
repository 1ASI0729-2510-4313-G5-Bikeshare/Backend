package com.bikeshare.backend.bikeInventory.domain.services;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bike;
import com.bikeshare.backend.bikeInventory.domain.model.commands.*;

import java.util.Optional;

/**
 * BikeCommandService
 * Service that handles bike commands
 */
public interface BikeCommandService {

    /**
     * Handle a create bike command
     * @param command he create bike command containing the bike data
     * @return The created bike
     * @see CreateBikeCommand
     */
    Long handle(CreateBikeCommand command);

    /**
     * Handle an update bike command
     * @param command The update bike command containing the bike data
     * @return The updated bike
     * @see UpdateBikeCommand
     */
    Optional<Bike> handle(UpdateBikeCommand command);

    /**
     * Handle a delete bike command
     * @param command The delete bike command containing the bike id.
     * @see DeleteBikeCommand
     */
    void handle(DeleteBikeCommand command);
}
