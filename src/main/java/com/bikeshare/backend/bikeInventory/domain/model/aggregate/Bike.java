package com.bikeshare.backend.bikeInventory.domain.model.aggregate;

import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikeCommand;
import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.*;
import com.bikeshare.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Bike aggregate root
 * @summary
 * This aggregate root represents a bike.
 * A course is a learning resource with a Onwer id, model, type, status, location, image url, cost per minute.
 * @see OwnerId
 * @see Coordinates
 * @see ImageUrl
 * @see Money
 * @since 1.0
 */
@Entity
@Getter
public class Bike extends AuditableAbstractAggregateRoot<Bike> {

    @Embedded
    @Column(name = "owner_id")
    private OwnerId ownerId;

    @Column(nullable = false)
    private String model;

    private BikeTypes type;

    private BikeStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "location_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "location_longitude"))
    })
    private Coordinates location;


    @Embedded
    @Column(name = "image_url")
    private ImageUrl imageUrl;

    @Embedded
    @Column(name = "cost_per_minute")
    private Money costPerMinute;

    public Bike() {
    }

    public Bike(CreateBikeCommand command){
        this.ownerId = new OwnerId(command.ownerId());
        this.model = command.model();
        this.type = command.type();
        this.status = BikeStatus.AVAILABLE; // inicializado por defecto
        this.location = new Coordinates(command.latitude(), command.longitude());
        this.imageUrl = new ImageUrl(command.imageUrl());
        this.costPerMinute = new Money(command.costPerMinute(), Currency.getInstance("PEN"));
    }

    public Bike updateBike(String model,
                           BikeTypes type,
                           BikeStatus newStatus,
                           Float latitude,
                           Float longitude,
                           String imageUrl,
                           BigDecimal newCostPerMinute) {
        this.model = model;
        this.type = type;
        this.status = newStatus;
        this.location = new Coordinates(latitude, longitude);
        this.imageUrl = new ImageUrl(imageUrl);
        this.costPerMinute = new Money(newCostPerMinute, Currency.getInstance("PEN"));
        return this;
    }

}
