package com.bikeshare.backend.rentalOperations.domain.model.queries;

public record GetRentalsByBikeIdClientIdAndStartTime(Long bikeId, Long clientId, String startTime) {
}
