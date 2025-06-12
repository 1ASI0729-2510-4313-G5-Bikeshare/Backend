package com.bikeshare.backend.bikeInventory.domain.services;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeTypes;
import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikeTypesCommand;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetAllBikeTypesQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetBikeTypesByIdQuery;

import java.util.List;
import java.util.Optional;

public interface BikeTypesQueryService {
    Optional<BikeTypes> handle(GetBikeTypesByIdQuery query);

    List<BikeTypes> handle(GetAllBikeTypesQuery query);
}
