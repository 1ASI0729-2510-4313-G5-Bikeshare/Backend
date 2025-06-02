package com.bikeshare.backend.geolocationNavigation.domain.model.aggregate;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
public class GeoZones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zone_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @CreatedDate
    private Date created_at;
}
