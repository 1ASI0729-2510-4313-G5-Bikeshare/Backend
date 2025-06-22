package com.bikeshare.backend.bikeInventory.domain.model.queries;

import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.OwnerId;

/**
 * Query to get bike by Ownerid.
 * @param ownerId Owner id.
 */
public record GetBikesByOwnerIdQuery(OwnerId ownerId) {
}
