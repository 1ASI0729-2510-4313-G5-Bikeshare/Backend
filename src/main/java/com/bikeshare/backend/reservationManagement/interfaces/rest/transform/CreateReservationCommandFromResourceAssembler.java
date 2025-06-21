package com.bikeshare.backend.reservationManagement.interfaces.rest.transform;

import com.bikeshare.backend.reservationManagement.domain.model.commands.CreateReservationCommand;
import com.bikeshare.backend.reservationManagement.interfaces.rest.resources.CreateReservationResource;

public class CreateReservationCommandFromResourceAssembler {

    public static CreateReservationCommand toCommandFromResource(CreateReservationResource resource) {
        return new CreateReservationCommand(
                resource.bikeId(),
                resource.clientId(),
                resource.startTime(),
                resource.endTime(),
                resource.statusId(),
                resource.price()
        );
    }
}
