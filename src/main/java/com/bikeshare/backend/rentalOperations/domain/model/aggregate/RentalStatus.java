package com.bikeshare.backend.rentalOperations.domain.model.aggregate;

import com.bikeshare.backend.rentalOperations.domain.model.commands.CreateRentalStatusCommand;
import com.fasterxml.jackson.core.base.GeneratorBase;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class RentalStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    @Column(nullable = false)
    private String statusName;

    protected RentalStatus() {};

    public RentalStatus(CreateRentalStatusCommand command) {
        this.statusName = command.statusName();
    }
}
