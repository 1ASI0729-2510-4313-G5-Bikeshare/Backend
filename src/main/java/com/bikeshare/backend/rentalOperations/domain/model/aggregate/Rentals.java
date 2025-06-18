package com.bikeshare.backend.rentalOperations.domain.model.aggregate;


import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bikes;
import com.bikeshare.backend.rentalOperations.domain.model.commands.CreateRentalCommand;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Getter
public class Rentals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;

    @ManyToOne
    @JoinColumn(name= "bike_id",nullable = false)
    private Bikes bikeId;

    @ManyToOne
    @JoinColumn(name = "client_id",nullable = false)
    private Users clientId;

    @Column(nullable = true)
    private String startTime;

    @Column(nullable = true)
    private String endTime;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private RentalStatus statusId;

    @Column(nullable = false)
    private Double price;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;

    protected Rentals() {};

    public Rentals(CreateRentalCommand command) {
        this.bikeId= command.bikeId();
        this.clientId= command.clientId();
        this.startTime= command.startTime();
        this.endTime= command.endTime();
        this.price= command.price();
    }

}
