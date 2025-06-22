package com.bikeshare.backend.reservationManagement.domain.services;

import com.bikeshare.backend.reservationManagement.domain.model.commands.CreateReservationCommand;


public interface ReservationCommandService {

    boolean deleteReservation(Long rentalId);

    Long handle(CreateReservationCommand command);
}
