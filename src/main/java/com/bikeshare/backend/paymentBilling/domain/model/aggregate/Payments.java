package com.bikeshare.backend.paymentBilling.domain.model.aggregate;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_id;

    @Column(nullable = false)
    private Integer rental_id;

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false)
    private  Float amount;

    @CreatedDate
    private Date timestamp;

    @Column
    private Integer status_id;
}
