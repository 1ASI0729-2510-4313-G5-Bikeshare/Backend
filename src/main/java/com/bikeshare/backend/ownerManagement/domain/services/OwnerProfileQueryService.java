package com.bikeshare.backend.ownerManagement.domain.services;

import com.bikeshare.backend.ownerManagement.domain.model.aggregate.OwnerProfiles;
import com.bikeshare.backend.ownerManagement.domain.model.queries.GetAllOwnerProfilesQuery;
import com.bikeshare.backend.ownerManagement.domain.model.queries.GetOwnerProfileById;
import com.bikeshare.backend.ownerManagement.domain.model.queries.GetOwnerProfileByTotalEarnings;

import java.util.List;
import java.util.Optional;

public interface OwnerProfileQueryService {

    Optional<OwnerProfiles> handle(GetOwnerProfileById query);

    List<OwnerProfiles> handle(GetOwnerProfileByTotalEarnings query);

    List<OwnerProfiles> handle(GetAllOwnerProfilesQuery query);
}
