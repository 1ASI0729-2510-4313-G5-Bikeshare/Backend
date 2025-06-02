package com.bikeshare.backend.rentalOperations.domain.model.aggregate;

import com.fasterxml.jackson.core.base.GeneratorBase;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class RentalStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer status_id;

    @Column(nullable = false)
    private String status_name;
}
