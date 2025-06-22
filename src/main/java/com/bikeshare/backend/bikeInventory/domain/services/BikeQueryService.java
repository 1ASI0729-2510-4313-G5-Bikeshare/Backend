package com.bikeshare.backend.bikeInventory.domain.services;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bike;
import com.bikeshare.backend.bikeInventory.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface BikeQueryService {

    Optional<Bike> handle(GetBikeByIdQuery query);

    Optional<Bike> handle(GetBikesByOwnerIdQuery query);

    List<Bike> handle(GetAllBikesQuery query);

    List<Bike> handle(GetAvailableBikesQuery query);

    //Coordinates handle(GetBikeLocationQuery query);

    List<String> handle(GetAllBikeTypesQuery query);

    List<String> handle(GetAllBikeStatusQuery query);

    boolean handle(ExistsByModelAndTypeQuery query);

    boolean handle(ExistsByModelAndTypeAndIdNot query);
}
