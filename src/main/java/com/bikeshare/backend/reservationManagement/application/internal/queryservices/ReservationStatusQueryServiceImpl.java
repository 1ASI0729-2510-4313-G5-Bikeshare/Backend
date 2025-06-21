package com.bikeshare.backend.reservationManagement.application.internal.queryservices;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.ReservationStatus;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetAllReservationStatusQuery;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetReservationStatusByIdQuery;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetReservationStatusByStatusNameQuery;
import com.bikeshare.backend.reservationManagement.domain.services.ReservationStatusQueryService;
import com.bikeshare.backend.reservationManagement.infrastructure.persistence.jpa.ReservationStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationStatusQueryServiceImpl implements ReservationStatusQueryService {

    private final ReservationStatusRepository reservationStatusRepository;

    ReservationStatusQueryServiceImpl(ReservationStatusRepository reservationStatusRepository) {
        this.reservationStatusRepository = reservationStatusRepository;
    }

    @Override
    public Optional<ReservationStatus> handle(GetReservationStatusByIdQuery query) {
        return reservationStatusRepository.findById(query.statusId());
    }

    @Override
    public Optional<ReservationStatus> handle(GetReservationStatusByStatusNameQuery query) {
        return reservationStatusRepository.findByStatusName(query.statusName());
    }

    @Override
    public List<ReservationStatus> handle(GetAllReservationStatusQuery query) {
        return reservationStatusRepository.findAll();
    }
}
