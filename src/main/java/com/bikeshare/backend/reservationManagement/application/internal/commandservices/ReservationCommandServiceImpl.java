package com.bikeshare.backend.reservationManagement.application.internal.commandservices;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.Reservations;
import com.bikeshare.backend.reservationManagement.domain.model.commands.CreateReservationCommand;
import com.bikeshare.backend.reservationManagement.domain.services.ReservationCommandService;
import com.bikeshare.backend.reservationManagement.infrastructure.persistence.jpa.ReservationsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {

    private final ReservationsRepository reservationsRepository;

    ReservationCommandServiceImpl(ReservationsRepository reservationsRepository) {
        this.reservationsRepository = reservationsRepository;
    }

    @Override
    public Optional<Reservations> handle(CreateReservationCommand command) {
        var rental = new Reservations(command);
        if(reservationsRepository.existsByBikeId_BikeIdAndClientId_UserIdAndStartTime(command.bikeId().getBikeId(), command.renterId().getUserId(), command.startTime())){
            throw new IllegalArgumentException("bikeId and UserId and startTime cannot be the same");
        }

        reservationsRepository.save(rental);
        return Optional.of(rental);

    }

    @Override
    public boolean deleteRental(Long rentalId) {
        Optional<Reservations> rentalsOptional = reservationsRepository.findById(rentalId);
        if(rentalsOptional.isPresent()){
            reservationsRepository.delete(rentalsOptional.get());
            return true;
        }
        return false;
    }

}
