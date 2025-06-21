package com.bikeshare.backend.reservationManagement.interfaces.rest.transform;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.ReservationStatus;
import com.bikeshare.backend.reservationManagement.interfaces.rest.resources.ReservationStatusResource;

public class ReservationStatusResourceFromEntityAssembler {

    public static ReservationStatusResource toResourceFromEntity(ReservationStatus entity) {
        return new ReservationStatusResource(entity.getStatusName());
    }
}
