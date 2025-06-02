package com.bikeshare.backend.bikeInventory.domain.model.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Getter
public class Bikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bike_id;

    @Column(nullable = false)
    private Long owner_id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer type_id;

    @Column(nullable = false)
    private Integer status_id;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;
}
