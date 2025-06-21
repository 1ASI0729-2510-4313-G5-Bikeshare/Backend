package com.bikeshare.backend.reservationManagement.interfaces.rest.transform;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.Reservations;
import com.bikeshare.backend.reservationManagement.interfaces.rest.resources.ReservationResource;

public class ReservationResourceFromEntityAssembler {

    public static ReservationResource toResourceFromEntity(Reservations entity){
        return new ReservationResource(
                entity.getBikeId(),
                entity.getRentertId(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getStatusId(),
                entity.getPrice()
        );
    }
}
