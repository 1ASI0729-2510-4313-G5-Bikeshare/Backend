package com.bikeshare.backend.bikeInventory.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Value object representing a monetary amount with currency.
 * @summary
 * This value object is used to encapsulate and validate a monetary amount associated with a bike (e.g., cost per minute).
 * It ensures the amount is greater than zero and the currency is not null.
 * Currently, only the Peruvian Sol (PEN) currency is supported.
 * It is an embeddable object used in entities such as Bike.
 *
 * Throws an {@link IllegalArgumentException} if:
 * - The amount is null or not greater than zero.
 * - The currency is null.
 * - The currency is not PEN.
 *
 * @param amount The monetary amount. Must be greater than zero.
 * @param currency The currency. Only PEN is supported.
 *
 * @see IllegalArgumentException
 * @since 1.0
 */
@Embeddable
public record Money(BigDecimal amount, Currency currency) {
    public Money {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency must not be null");
        }

        // Por ahora solo permitimos PEN
        if (!Currency.getInstance("PEN").equals(currency)) {
            throw new IllegalArgumentException("Only PEN is supported at this time");
        }
    }
}
