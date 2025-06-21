package com.bikeshare.backend.reservationManagement.interfaces.rest.resources;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bikes;
import com.bikeshare.backend.reservationManagement.domain.model.aggregate.ReservationStatus;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record ReservationResource(
        Bikes bikeId,
        Users clientId,
        String startTime,
        String endTime,
        ReservationStatus statusId,
        Double price
) {
}
