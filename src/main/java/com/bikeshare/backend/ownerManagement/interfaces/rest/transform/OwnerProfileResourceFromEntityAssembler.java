package com.bikeshare.backend.ownerManagement.interfaces.rest.transform;

import com.bikeshare.backend.ownerManagement.domain.model.aggregate.OwnerProfiles;
import com.bikeshare.backend.ownerManagement.interfaces.rest.resources.OwnerProfileResource;

public class OwnerProfileResourceFromEntityAssembler {

    public static OwnerProfileResource toResourceFromEntity(OwnerProfiles entity) {
        return new OwnerProfileResource(
                entity.getLenderId(),
                entity.getBio(),
                entity.getTotalEarnings(),
                entity.getRating()
        );
    }
}
