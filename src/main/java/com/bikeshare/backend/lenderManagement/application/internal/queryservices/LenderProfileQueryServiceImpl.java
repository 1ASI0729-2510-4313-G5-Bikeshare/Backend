package com.bikeshare.backend.lenderManagement.application.internal.queryservices;

import com.bikeshare.backend.lenderManagement.domain.model.aggregate.LenderProfiles;
import com.bikeshare.backend.lenderManagement.domain.model.queries.GetAllLenderProfilesQuery;
import com.bikeshare.backend.lenderManagement.domain.model.queries.GetLenderProfileById;
import com.bikeshare.backend.lenderManagement.domain.model.queries.GetLenderProfileByTotalEarnings;
import com.bikeshare.backend.lenderManagement.domain.services.LenderProfileQueryService;
import com.bikeshare.backend.lenderManagement.infrastructure.persistence.jpa.LenderProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LenderProfileQueryServiceImpl implements LenderProfileQueryService {

    private final LenderProfilesRepository lenderProfilesRepository;

    LenderProfileQueryServiceImpl(LenderProfilesRepository lenderProfilesRepository) {
        this.lenderProfilesRepository = lenderProfilesRepository;
    }

    @Override
    public Optional<LenderProfiles> handle(GetLenderProfileById query) {
        return lenderProfilesRepository.findByLenderId_UserId(query.lenderId());
    }

    @Override
    public List<LenderProfiles> handle(GetLenderProfileByTotalEarnings query) {
        return this.lenderProfilesRepository.findAllByTotalEarningsGreaterThan(query.totalEarnings());
    }

    @Override
    public List<LenderProfiles> handle(GetAllLenderProfilesQuery query) {
        return lenderProfilesRepository.findAll();
    }
}
