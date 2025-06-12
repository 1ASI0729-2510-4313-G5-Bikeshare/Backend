package com.bikeshare.backend.bikeInventory.domain.services;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeStatus;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetAllBikeStatusQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetBikeStatusByIdQuery;

import java.util.List;
import java.util.Optional;

public interface BikeStatusQueryService {
    Optional<BikeStatus> handle(GetBikeStatusByIdQuery query);

    List<BikeStatus> handle(GetAllBikeStatusQuery query);
}
