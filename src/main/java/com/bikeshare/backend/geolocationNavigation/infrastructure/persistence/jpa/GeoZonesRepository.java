package com.bikeshare.backend.geolocationNavigation.infrastructure.persistence.jpa;

import com.bikeshare.backend.geolocationNavigation.domain.model.aggregate.GeoZones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoZonesRepository extends JpaRepository<GeoZones,Long> {
}
