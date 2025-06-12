package com.bikeshare.backend.lenderManagement.interfaces.rest.transform;

import com.bikeshare.backend.lenderManagement.domain.model.aggregate.LenderProfiles;
import com.bikeshare.backend.lenderManagement.interfaces.rest.resources.LenderProfileResource;

public class LenderProfileResourceFromEntityAssembler {

    public static LenderProfileResource toResourceFromEntity(LenderProfiles entity) {
        return new LenderProfileResource(
                entity.getLenderId(),
                entity.getBio(),
                entity.getTotalEarnings(),
                entity.getRating()
        );
    }
}
