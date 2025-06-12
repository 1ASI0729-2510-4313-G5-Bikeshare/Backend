package com.bikeshare.backend.bikeInventory.application.internal.queryservices;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeTypes;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetAllBikeTypesQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetBikeTypesByIdQuery;
import com.bikeshare.backend.bikeInventory.domain.services.BikeTypesQueryService;
import com.bikeshare.backend.bikeInventory.infrastructure.persistence.jpa.BikeTypesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeTypesQueryServiceImpl implements BikeTypesQueryService {

    private final BikeTypesRepository bikeTypesRepository;

    BikeTypesQueryServiceImpl(BikeTypesRepository bikeTypesRepository) {
        this.bikeTypesRepository = bikeTypesRepository;
    }

    @Override
    public Optional<BikeTypes> handle(GetBikeTypesByIdQuery query) {
        return this.bikeTypesRepository.findById(query.typeId());
    }

    @Override
    public List<BikeTypes> handle(GetAllBikeTypesQuery query) {
        return this.bikeTypesRepository.findAll();
    }

}
