package com.bikeshare.backend.rentalOperations.application.internal.queryservices;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.RentalStatus;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetAllRentalStatusQuery;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetRentalStatusByIdQuery;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetRentalStatusByStatusNameQuery;
import com.bikeshare.backend.rentalOperations.domain.services.RentalStatusQueryService;
import com.bikeshare.backend.rentalOperations.infrastructure.persistence.jpa.RentalStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalStatusQueryServiceImpl implements RentalStatusQueryService {

    private final RentalStatusRepository rentalStatusRepository;

    RentalStatusQueryServiceImpl(RentalStatusRepository rentalStatusRepository) {
        this.rentalStatusRepository = rentalStatusRepository;
    }

    @Override
    public Optional<RentalStatus> handle(GetRentalStatusByIdQuery query) {
        return rentalStatusRepository.findById(query.statusId());
    }

    @Override
    public Optional<RentalStatus> handle(GetRentalStatusByStatusNameQuery query) {
        return rentalStatusRepository.findByStatusName(query.statusName());
    }

    @Override
    public List<RentalStatus> handle(GetAllRentalStatusQuery query) {
        return rentalStatusRepository.findAll();
    }
}
