package com.bikeshare.backend.reservationManagement.domain.services;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.ReservationStatus;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetAllReservationStatusQuery;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetReservationStatusByIdQuery;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetReservationStatusByStatusNameQuery;

import java.util.List;
import java.util.Optional;

public interface ReservationStatusQueryService {

    Optional<ReservationStatus> handle(GetReservationStatusByIdQuery query);

    Optional<ReservationStatus> handle(GetReservationStatusByStatusNameQuery query);

    List<ReservationStatus> handle(GetAllReservationStatusQuery query);
}
