package com.bikeshare.backend.reservationManagement.domain.model.commands;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.ReservationStatus;
import com.bikeshare.backend.reservationManagement.domain.model.valueObjects.BikeId;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record CreateReservationCommand(
        BikeId bikeId,
        Users renter,
        String startTime,
        String endTime,
        ReservationStatus statusId,
        Double price
) {
}
