package com.bikeshare.backend.paymentBilling.domain.model.aggregate;

import com.bikeshare.backend.paymentBilling.domain.model.commands.CreatePaymentCommand;
import com.bikeshare.backend.rentalOperations.domain.model.aggregate.RentalStatus;
import com.bikeshare.backend.rentalOperations.domain.model.aggregate.Rentals;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.apache.catalina.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_id;

    @OneToOne
    @JoinColumn(name = "rental_id",nullable = false)
    private Rentals rentalId;

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
