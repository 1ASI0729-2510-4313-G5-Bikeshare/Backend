package com.bikeshare.backend.rentalOperations.interfaces.rest.resources;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bikes;
import com.bikeshare.backend.rentalOperations.domain.model.aggregate.RentalStatus;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record RentalResource(
        Bikes bikeId,
        Users clientId,
        String startTime,
        String endTime,
        RentalStatus statusId,
        Double price
) {
}
