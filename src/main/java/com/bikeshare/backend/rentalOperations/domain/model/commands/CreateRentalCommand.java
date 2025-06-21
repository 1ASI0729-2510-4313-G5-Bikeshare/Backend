package com.bikeshare.backend.rentalOperations.domain.model.commands;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bikes;
import com.bikeshare.backend.rentalOperations.domain.model.aggregate.RentalStatus;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import org.apache.catalina.User;

public record CreateRentalCommand(
        Bikes bikeId,
        Users renterId,
        String startTime,
        String endTime,
        RentalStatus statusId,
        Double price
) {
}
