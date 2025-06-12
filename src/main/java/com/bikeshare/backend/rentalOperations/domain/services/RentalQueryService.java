package com.bikeshare.backend.rentalOperations.domain.services;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.Rentals;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetAllRentalsQuery;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetRentalByIdQuery;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetRentalsByBikeIdClientIdAndStartTime;

import java.util.List;
import java.util.Optional;

public interface RentalQueryService {

    Optional<Rentals> handle(GetRentalByIdQuery query);

    Optional<Rentals> handle(GetRentalsByBikeIdClientIdAndStartTime query);

    List<Rentals> handle(GetAllRentalsQuery query);
}
