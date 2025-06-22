package com.bikeshare.backend.reservationManagement.interfaces.rest.transform;

import com.bikeshare.backend.reservationManagement.domain.model.commands.CreateReservationStatusCommand;
import com.bikeshare.backend.reservationManagement.interfaces.rest.resources.CreateReservationStatusResource;

public class CreateReservationStatusCommandFromResourceAssembler {

    public static CreateReservationStatusCommand toCommandFromResource(CreateReservationStatusResource resource) {
        return new CreateReservationStatusCommand(resource.statusName());
    }
}
