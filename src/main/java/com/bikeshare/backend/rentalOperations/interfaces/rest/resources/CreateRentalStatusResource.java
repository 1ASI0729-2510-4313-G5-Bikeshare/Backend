package com.bikeshare.backend.rentalOperations.interfaces.rest.resources;

public record CreateRentalStatusResource(
        String statusName
) {
    public CreateRentalStatusResource {
        if(statusName.isEmpty()) throw new IllegalArgumentException("statusName cannot be empty");
    }
}
