package com.bikeshare.backend.lenderManagement.domain.model.aggregate;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Getter
public class LenderProfiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lender_id;

    @Column(nullable = false)
    private String bio;

    @Column(nullable = false)
    private Double total_earnings;

    @Column(nullable = false)
    private Float rating;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

}
