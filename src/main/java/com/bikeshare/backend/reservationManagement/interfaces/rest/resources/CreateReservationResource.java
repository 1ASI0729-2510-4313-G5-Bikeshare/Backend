package com.bikeshare.backend.reservationManagement.interfaces.rest.resources;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.ReservationStatus;
import com.bikeshare.backend.reservationManagement.domain.model.valueObjects.BikeId;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record CreateReservationResource(
        BikeId bikeId,
        Users clientId,
        String startTime,
        String endTime,
        ReservationStatus statusId,
        Double price
) {

    public CreateReservationResource {
        if(bikeId == null){
            throw new IllegalArgumentException("Id cannot be null");
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
            throw new IllegalArgumentException("newStatus cannot be null");
        }
        if(price == null){
            throw new IllegalArgumentException("price cannot be null");
        }
    }
}
