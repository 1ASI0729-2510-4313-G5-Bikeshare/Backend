package com.bikeshare.backend.rentalOperations.application.internal.queryservices;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.Rentals;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetAllRentalsQuery;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetRentalByIdQuery;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetRentalsByBikeIdClientIdAndStartTime;
import com.bikeshare.backend.rentalOperations.domain.services.RentalQueryService;
import com.bikeshare.backend.rentalOperations.infrastructure.persistence.jpa.RentalsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalQueryServiceImpl implements RentalQueryService {

    private final RentalsRepository rentalsRepository;

    RentalQueryServiceImpl(RentalsRepository rentalsRepository) {
        this.rentalsRepository = rentalsRepository;
    }

    @Override
    public Optional<Rentals> handle(GetRentalByIdQuery query) {
        return rentalsRepository.findById(query.rentalId());
    }

    @Override
    public Optional<Rentals> handle(GetRentalsByBikeIdClientIdAndStartTime query) {
        return rentalsRepository.findByBikeId_BikeIdAndClientId_UserIdAndStartTime(query.bikeId(), query.clientId(), query.startTime());
    }

    @Override
    public List<Rentals> handle(GetAllRentalsQuery query) { return rentalsRepository.findAll();}
}
