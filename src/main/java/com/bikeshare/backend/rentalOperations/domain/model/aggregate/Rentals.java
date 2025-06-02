package com.bikeshare.backend.rentalOperations.domain.model.aggregate;


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
    private Long rental_id;

    @Column(nullable = false)
    private  Integer bike_id;

    @Column(nullable = false)
    private Integer client_id;

    @CreatedDate
    private Date start_time;

    @LastModifiedDate
    private Date end_time;

    @Column(nullable = false)
    private DecimalFormat price;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;
}
