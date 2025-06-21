package com.bikeshare.backend.reservationManagement.domain.model.aggregate;

import com.bikeshare.backend.reservationManagement.domain.model.commands.CreateReservationStatusCommand;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ReservationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    @Column(nullable = false)
    private String statusName;

    protected ReservationStatus() {};

    public ReservationStatus(CreateReservationStatusCommand command) {
        this.statusName = command.statusName();
    }
}
