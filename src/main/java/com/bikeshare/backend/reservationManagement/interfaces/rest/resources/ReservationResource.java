package com.bikeshare.backend.reservationManagement.interfaces.rest.resources;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.ReservationStatus;
import com.bikeshare.backend.reservationManagement.domain.model.valueObjects.BikeId;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record ReservationResource(
        BikeId bikeId,
        Users clientId,
        String startTime,
        String endTime,
        ReservationStatus statusId,
        Double price
) {
}
