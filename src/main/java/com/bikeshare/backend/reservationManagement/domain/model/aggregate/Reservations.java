package com.bikeshare.backend.reservationManagement.domain.model.aggregate;


import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bike;
import com.bikeshare.backend.reservationManagement.domain.model.commands.CreateReservationCommand;
import com.bikeshare.backend.reservationManagement.domain.model.valueObjects.BikeId;
import com.bikeshare.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Getter
public class Reservations extends AuditableAbstractAggregateRoot<Reservations> {

    @Embedded
    private BikeId bikeId;

    @ManyToOne
    @JoinColumn(name = "renter_id",nullable = false)
    private Users renter;

    @Column(nullable = true)
    private String startTime;

    @Column(nullable = true)
    private String endTime;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private ReservationStatus statusId;

    @Column(nullable = false)
    private Double price;


    protected Reservations() {};

    public Reservations(CreateReservationCommand command) {
        this.bikeId= command.bikeId();
        this.renter= command.renter();
        this.startTime= command.startTime();
        this.endTime= command.endTime();
        this.price= command.price();
    }

}
