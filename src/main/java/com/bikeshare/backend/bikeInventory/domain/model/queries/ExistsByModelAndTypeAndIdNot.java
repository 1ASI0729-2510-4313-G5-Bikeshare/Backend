package com.bikeshare.backend.bikeInventory.domain.model.queries;

import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeTypes;

/**
 * ExistsByModelAndTypeAndIdNotQuery
 * <p>This query is used to check if a bike with the specified model and type exists,
 * excluding the bike with the provided identifier.</p>
 *
 * @param model the bike's model.
 * @param type the bike's type.
 * @param id the identifier of the bike to exclude.
 */
public record ExistsByModelAndTypeAndIdNot(String model, BikeTypes type, Long id) {
}
