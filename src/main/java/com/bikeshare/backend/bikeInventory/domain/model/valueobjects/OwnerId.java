package com.bikeshare.backend.bikeInventory.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value object representing the owner id.
 * @summary
 * This value object is used to represent the owner id. It is an embeddable object that is used to represent the owner id in the bike entity.
 * It throws an IllegalArgumentException if the owner id is null or less than 1.
 * @param ownerId The owner id. It cannot be null or less than 1.
 * @see IllegalArgumentException
 * @since 1.0
 */
@Embeddable
public record OwnerId(Long ownerId) {
    public OwnerId {
        if (ownerId == null || ownerId < 1) {
            throw new IllegalArgumentException("Owner id cannot be null or less than 1");
        }
    }

    /**
     * Factory method to create an {@link OwnerId} from a String.
     * @param value The value of the string
     * @return an instance of {@link OwnerId}.
     */
    public static OwnerId valueOf(String value) {
        return new OwnerId(Long.valueOf(value));
    }
}
