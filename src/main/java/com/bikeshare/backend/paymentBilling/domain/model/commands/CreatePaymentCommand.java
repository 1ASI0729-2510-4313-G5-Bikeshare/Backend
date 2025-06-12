package com.bikeshare.backend.paymentBilling.domain.model.commands;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.RentalStatus;
import com.bikeshare.backend.rentalOperations.domain.model.aggregate.Rentals;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record CreatePaymentCommand(
        Rentals rentalId,
        Users userId,
        Double amount,
        RentalStatus statusId
) {
}
