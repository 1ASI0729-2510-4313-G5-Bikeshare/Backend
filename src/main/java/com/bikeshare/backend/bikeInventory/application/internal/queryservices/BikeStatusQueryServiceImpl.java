package com.bikeshare.backend.bikeInventory.application.internal.queryservices;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeStatus;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetAllBikeStatusQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetBikeStatusByIdQuery;
import com.bikeshare.backend.bikeInventory.domain.services.BikeStatusQueryService;
import com.bikeshare.backend.bikeInventory.infrastructure.persistence.jpa.BikeStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BikeStatusQueryServiceImpl implements BikeStatusQueryService {

    private BikeStatusRepository bikeStatusRepository;

    BikeStatusQueryServiceImpl(BikeStatusRepository bikeStatusRepository) {
        this.bikeStatusRepository = bikeStatusRepository;
    }

    @Override
    public Optional<BikeStatus> handle(GetBikeStatusByIdQuery query) {
        return this.bikeStatusRepository.findById(query.statusId());
    }

    @Override
    public List<BikeStatus> handle(GetAllBikeStatusQuery query) {
        return this.bikeStatusRepository.findAll();
    }
}
