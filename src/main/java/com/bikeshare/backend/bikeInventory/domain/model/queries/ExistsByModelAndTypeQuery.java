package com.bikeshare.backend.bikeInventory.domain.model.queries;

import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeTypes;

/**
 * ExistsByModelAndTypeQuery
 * <p>
 * This query is used to check if a bike exists with the specified model and type.
 * </p>
 *
 * @param model the bike's model.
 * @param type  the bike's type.
 */
public record ExistsByModelAndTypeQuery(String model, BikeTypes type) {
}
