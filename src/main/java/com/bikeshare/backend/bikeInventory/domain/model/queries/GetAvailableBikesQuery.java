package com.bikeshare.backend.bikeInventory.domain.model.queries;

import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeTypes;

import java.math.BigDecimal;

/**
 * Query to get available bikes filtered by type and price range.
 *
 * @param type      The type of bike to filter by (e.g., ELECTRIC, SPORT). Can be null.
 * @param minPrice  The minimum price per minute. Can be null.
 *                  If provided, must be greater than 0.
 * @param maxPrice  The maximum price per minute. Can be null.
 *                  If provided, must be greater than 0 and not less than minPrice.
 */
public record GetAvailableBikesQuery(BikeTypes type,
                                     BigDecimal minPrice,
                                     BigDecimal maxPrice
) {
    /**
     * Constructor for {@link GetAvailableBikesQuery}.
     *
     * @throws IllegalArgumentException if:
     * <ul>
     *   <li>{@code minPrice} is not null and less than or equal to 0</li>
     *   <li>{@code maxPrice} is not null and less than or equal to 0</li>
     *   <li>{@code maxPrice} is not null and less than {@code minPrice}</li>
     * </ul>
     */
    public GetAvailableBikesQuery {
        if (minPrice!=null) {
            if (minPrice.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("min Price cannot be less than zero");
            }
        }
        if (maxPrice !=null) {
            if (maxPrice.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("The maximum price must be greater than zero");
            }
            if (minPrice != null && maxPrice.compareTo(minPrice) < 0) {
                throw new IllegalArgumentException("The maximum price must be greater than or equal to the minimum price");
            }
        }
    }
}
