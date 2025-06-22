package com.bikeshare.backend.bikeInventory.application.internal.queryservices;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bike;
import com.bikeshare.backend.bikeInventory.domain.model.queries.*;
import com.bikeshare.backend.bikeInventory.domain.services.BikeQueryService;
import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeStatus;
import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeTypes;
import com.bikeshare.backend.bikeInventory.infrastructure.persistence.jpa.BikesRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BikeQueryServiceImpl implements BikeQueryService {

    private final BikesRepository bikesRepository;

    BikeQueryServiceImpl(BikesRepository bikesRepository) {
        this.bikesRepository = bikesRepository;
    }

    @Override
    public Optional<Bike> handle(GetBikeByIdQuery query) {
        return this.bikesRepository.findById(query.id());
    }

    @Override
    public Optional<Bike> handle(GetBikesByOwnerIdQuery query) {
        return this.bikesRepository.findByOwnerId(query.ownerId());
    }

    @Override
    public List<Bike> handle(GetAllBikesQuery query) {
        return this.bikesRepository.findAll();
    }

    @Override
    public List<Bike> handle(GetAvailableBikesQuery query) {
        return this.bikesRepository.findByStatus(BikeStatus.AVAILABLE);
    }
    /*
    @Override
    public Coordinates handle(GetBikeLocationQuery query) {
        return this.bikesRepository.findLocationByBikeId(query.Id());
    }
*/
    @Override
    public List<String> handle(GetAllBikeTypesQuery query) {
        return Arrays.stream(BikeTypes.values())
                .map(Enum::name)
                .toList();
    }

    @Override
    public List<String> handle(GetAllBikeStatusQuery query) {
        return Arrays.stream(BikeStatus.values())
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean handle(ExistsByModelAndTypeQuery query) {
        return this.bikesRepository.existsByModelAndType(query.model(), query.type());
    }

    @Override
    public boolean handle(ExistsByModelAndTypeAndIdNot query) {
        return this.bikesRepository.existsByModelAndTypeAndIdNot(query.model(), query.type(), query.id());
    }
}
