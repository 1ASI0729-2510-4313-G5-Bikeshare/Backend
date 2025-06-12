package com.bikeshare.backend.bikeInventory.domain.model.aggregate;

import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikeStatusCommand;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class BikeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    @Column(nullable = false)
    private String statusName;

    private BikeStatus() {};

    public BikeStatus(CreateBikeStatusCommand command) {
        this.statusName = command.statusName();
    }
}
