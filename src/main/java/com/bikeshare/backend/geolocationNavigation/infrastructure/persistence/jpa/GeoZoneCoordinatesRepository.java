package com.bikeshare.backend.geolocationNavigation.infrastructure.persistence.jpa;

import com.bikeshare.backend.geolocationNavigation.domain.model.aggregate.GeoZoneCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoZoneCoordinatesRepository extends JpaRepository<GeoZoneCoordinates, Long> {
}
