package com.bikeshare.backend.rentalOperations.domain.services;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.RentalStatus;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetAllRentalStatusQuery;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetRentalStatusByIdQuery;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetRentalStatusByStatusNameQuery;

import java.util.List;
import java.util.Optional;

public interface RentalStatusQueryService {

    Optional<RentalStatus> handle(GetRentalStatusByIdQuery query);

    Optional<RentalStatus> handle(GetRentalStatusByStatusNameQuery query);

    List<RentalStatus> handle(GetAllRentalStatusQuery query);
}
