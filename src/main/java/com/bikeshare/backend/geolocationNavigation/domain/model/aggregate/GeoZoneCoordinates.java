package com.bikeshare.backend.geolocationNavigation.domain.model.aggregate;


import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;

@Entity
@Getter
public class GeoZoneCoordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coord_id;

    @Column
    private Integer zone_id;

    @Column
    private Double latitude;

    @Column
    private Double longitude;
}
