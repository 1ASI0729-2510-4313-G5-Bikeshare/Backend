package com.bikeshare.backend.ownerManagement.application.internal.queryservices;

import com.bikeshare.backend.ownerManagement.domain.model.aggregate.OwnerProfiles;
import com.bikeshare.backend.ownerManagement.domain.model.queries.GetAllOwnerProfilesQuery;
import com.bikeshare.backend.ownerManagement.domain.model.queries.GetOwnerProfileById;
import com.bikeshare.backend.ownerManagement.domain.model.queries.GetOwnerProfileByTotalEarnings;
import com.bikeshare.backend.ownerManagement.domain.services.OwnerProfileQueryService;
import com.bikeshare.backend.ownerManagement.infrastructure.persistence.jpa.OwnerProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerProfileQueryServiceImpl implements OwnerProfileQueryService {

    private final OwnerProfilesRepository ownerProfilesRepository;

    OwnerProfileQueryServiceImpl(OwnerProfilesRepository ownerProfilesRepository) {
        this.ownerProfilesRepository = ownerProfilesRepository;
    }

    @Override
    public Optional<OwnerProfiles> handle(GetOwnerProfileById query) {
        return ownerProfilesRepository.findByLenderId_UserId(query.lenderId());
    }

    @Override
    public List<OwnerProfiles> handle(GetOwnerProfileByTotalEarnings query) {
        return this.ownerProfilesRepository.findAllByTotalEarningsGreaterThan(query.totalEarnings());
    }

    @Override
    public List<OwnerProfiles> handle(GetAllOwnerProfilesQuery query) {
        return ownerProfilesRepository.findAll();
    }
}
