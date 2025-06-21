package com.bikeshare.backend.reservationManagement.domain.services;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.Reservations;
import com.bikeshare.backend.reservationManagement.domain.model.commands.CreateReservationCommand;

import java.util.Optional;

public interface ReservationCommandService {

    boolean deleteRental(Long rentalId);

    Optional<Reservations> handle(CreateReservationCommand command);
}
