package com.bikeshare.backend.rentalOperations.interfaces.rest.resources;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bikes;
import com.bikeshare.backend.rentalOperations.domain.model.aggregate.RentalStatus;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record CreateRentalResource(
        Bikes bikeId,
        Users clientId,
        String startTime,
        String endTime,
        RentalStatus statusId,
        Double price
) {

    public CreateRentalResource{
        if(bikeId == null){
            throw new IllegalArgumentException("bikeId cannot be null");
        }
        if(clientId == null){
            throw new IllegalArgumentException("clientId cannot be null");
        }
        if(startTime == null){
            throw new IllegalArgumentException("startTime cannot be null");
        }
        if(endTime == null){
            throw new IllegalArgumentException("endTime cannot be null");
        }
        if(statusId == null){
            throw new IllegalArgumentException("statusId cannot be null");
        }
        if(price == null){
            throw new IllegalArgumentException("price cannot be null");
        }
    }
}
