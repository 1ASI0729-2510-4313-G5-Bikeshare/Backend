package com.bikeshare.backend.reservationManagement.domain.services;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.Reservations;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetAllReservationsQuery;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetReservationByIdQuery;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetReservationsByClientEmail;

import java.util.List;
import java.util.Optional;

public interface ReservationQueryService {

    Optional<Reservations> handle(GetReservationByIdQuery query);

    List<Reservations> handle(GetReservationsByClientEmail query);

    List<Reservations> handle(GetAllReservationsQuery query);
}
