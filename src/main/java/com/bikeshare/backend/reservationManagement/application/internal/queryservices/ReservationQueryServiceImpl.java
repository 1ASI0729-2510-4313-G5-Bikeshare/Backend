package com.bikeshare.backend.reservationManagement.application.internal.queryservices;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.Reservations;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetAllReservationsQuery;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetReservationByIdQuery;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetReservationsByClientEmail;
import com.bikeshare.backend.reservationManagement.domain.services.ReservationQueryService;
import com.bikeshare.backend.reservationManagement.infrastructure.persistence.jpa.ReservationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationQueryServiceImpl implements ReservationQueryService {

    private final ReservationsRepository reservationsRepository;

    ReservationQueryServiceImpl(ReservationsRepository reservationsRepository) {
        this.reservationsRepository = reservationsRepository;
    }

    @Override
    public Optional<Reservations> handle(GetReservationByIdQuery query) {
        return reservationsRepository.findById(query.rentalId());
    }

    @Override
    public List<Reservations> handle(GetReservationsByClientEmail query) {
        return reservationsRepository.findByClientId_Email(query.email());
    }

    @Override
    public List<Reservations> handle(GetAllReservationsQuery query) { return reservationsRepository.findAll();}
}
