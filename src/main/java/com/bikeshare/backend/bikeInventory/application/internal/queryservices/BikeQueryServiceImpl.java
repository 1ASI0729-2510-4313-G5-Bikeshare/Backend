package com.bikeshare.backend.bikeInventory.application.internal.queryservices;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bikes;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetAllBikesQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetBikesByIdQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetBikesByOwnerIdQuery;
import com.bikeshare.backend.bikeInventory.domain.services.BikeQueryService;
import com.bikeshare.backend.bikeInventory.infrastructure.persistence.jpa.BikesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeQueryServiceImpl implements BikeQueryService {

    private final BikesRepository bikesRepository;

    BikeQueryServiceImpl(BikesRepository bikesRepository) {
        this.bikesRepository = bikesRepository;
    }

    @Override
    public Optional<Bikes> handle(GetBikesByIdQuery query) {
        return this.bikesRepository.findById(query.bikeId());
    }

    @Override
    public Optional<Bikes> handle(GetBikesByOwnerIdQuery query) {
        return this.bikesRepository.findByOwnerId_UserId(query.ownerId());
    }

    @Override
    public List<Bikes> handle(GetAllBikesQuery query) {
        return this.bikesRepository.findAll();
    }
}
