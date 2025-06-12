package com.bikeshare.backend.bikeInventory.domain.model.queries;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record GetBikesByOwnerIdQuery(Long ownerId) {
}
