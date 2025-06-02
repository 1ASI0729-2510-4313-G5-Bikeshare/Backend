package com.bikeshare.backend.bikeInventory.domain.model.aggregate;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class BikeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long status_id;

    @Column(nullable = false)
    private String status_name;
}
