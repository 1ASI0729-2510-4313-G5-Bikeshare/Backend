package com.bikeshare.backend.paymentBilling.domain.model.aggregate;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoice_id;

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false)
    private Float amount;

    @Column(nullable = false)
    private Date issue_date;

    @CreatedDate
    private Date created_at;
}
