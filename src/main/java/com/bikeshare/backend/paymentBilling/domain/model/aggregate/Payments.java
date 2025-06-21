package com.bikeshare.backend.paymentBilling.domain.model.aggregate;

import com.bikeshare.backend.paymentBilling.domain.model.commands.CreatePaymentCommand;
import com.bikeshare.backend.reservationManagement.domain.model.aggregate.Reservations;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_id;

    @OneToOne
    @JoinColumn(name = "rental_id",nullable = false)
    private Reservations rentalId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Users userId;

    @Column(nullable = false)
    private  Double amount;

    @CreatedDate
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private PaymentStatus statusId;

    protected Payments() {};

    public Payments(CreatePaymentCommand command) {
        this.rentalId = command.rentalId();
        this.userId = command.userId();
        this.amount = command.amount();
    }
}
