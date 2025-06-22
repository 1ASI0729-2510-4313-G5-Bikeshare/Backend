package com.bikeshare.backend.reservationManagement.application.internal.commandservices;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.ReservationStatus;
import com.bikeshare.backend.reservationManagement.domain.model.commands.CreateReservationStatusCommand;
import com.bikeshare.backend.reservationManagement.domain.services.ReservationStatusCommandService;
import com.bikeshare.backend.reservationManagement.infrastructure.persistence.jpa.ReservationStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationStatusCommandServiceImpl implements ReservationStatusCommandService {

    private final ReservationStatusRepository reservationStatusRepository;

    ReservationStatusCommandServiceImpl(ReservationStatusRepository reservationStatusRepository) {
        this.reservationStatusRepository = reservationStatusRepository;
    }

    @Override
    public Optional<ReservationStatus> handle(CreateReservationStatusCommand command) {
        var rentalStatus = new ReservationStatus(command);

        if(reservationStatusRepository.existsByStatusName(command.statusName())) {
            throw new IllegalArgumentException("Rental Status already exists");
        }

        reservationStatusRepository.save(rentalStatus);
        return Optional.of(rentalStatus);
    }
}
