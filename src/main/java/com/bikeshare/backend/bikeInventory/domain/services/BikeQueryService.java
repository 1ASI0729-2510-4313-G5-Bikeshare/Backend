package com.bikeshare.backend.bikeInventory.domain.services;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bikes;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetAllBikesQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetBikesByIdQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetBikesByOwnerIdQuery;

import java.util.List;
import java.util.Optional;

public interface BikeQueryService {

    Optional<Bikes> handle(GetBikesByIdQuery query);

    Optional<Bikes> handle(GetBikesByOwnerIdQuery query);

    List<Bikes> handle(GetAllBikesQuery query);
}
