package com.bikeshare.backend.paymentBilling.domain.model.commands;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.ReservationStatus;
import com.bikeshare.backend.reservationManagement.domain.model.aggregate.Reservations;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record CreatePaymentCommand(
        Reservations rentalId,
        Users userId,
        Double amount,
        ReservationStatus statusId
) {
}
