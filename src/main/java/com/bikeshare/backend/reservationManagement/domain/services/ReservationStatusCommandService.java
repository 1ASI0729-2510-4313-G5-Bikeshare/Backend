package com.bikeshare.backend.reservationManagement.domain.services;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.ReservationStatus;
import com.bikeshare.backend.reservationManagement.domain.model.commands.CreateReservationStatusCommand;

import java.util.Optional;

public interface ReservationStatusCommandService {

    Optional<ReservationStatus> handle(CreateReservationStatusCommand command);
}
