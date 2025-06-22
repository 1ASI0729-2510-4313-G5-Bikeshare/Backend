package com.bikeshare.backend.bikeInventory.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value object representing the image URL of a bike.
 * @summary
 * This value object is used to encapsulate and validate the image URL associated with a bike.
 * It ensures the URL is neither null nor blank. It is an embeddable object used in the Bike entity.
 * It throws an IllegalArgumentException if the image URL is null or empty.
 * @param imageUrl The image URL. Cannot be null or blank.
 * @see IllegalArgumentException
 * @since 1.0
 */
@Embeddable
public record ImageUrl(String imageUrl) {
    public ImageUrl {
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("image Url can't be null or empty");
        }
    }
}


