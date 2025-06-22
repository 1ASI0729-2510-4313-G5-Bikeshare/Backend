package com.bikeshare.backend.reservationManagement.interfaces.rest.resources;

public record CreateReservationStatusResource(
        String statusName
) {
    public CreateReservationStatusResource {
        if(statusName.isEmpty()) throw new IllegalArgumentException("statusName cannot be empty");
    }
}
