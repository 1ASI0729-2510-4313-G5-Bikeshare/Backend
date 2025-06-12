package com.bikeshare.backend.lenderManagement.domain.services;

import com.bikeshare.backend.lenderManagement.domain.model.aggregate.LenderProfiles;
import com.bikeshare.backend.lenderManagement.domain.model.queries.GetAllLenderProfilesQuery;
import com.bikeshare.backend.lenderManagement.domain.model.queries.GetLenderProfileById;
import com.bikeshare.backend.lenderManagement.domain.model.queries.GetLenderProfileByTotalEarnings;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface LenderProfileQueryService {

    Optional<LenderProfiles> handle(GetLenderProfileById query);

    List<LenderProfiles> handle(GetLenderProfileByTotalEarnings query);

    List<LenderProfiles> handle(GetAllLenderProfilesQuery query);
}
