package com.airlinesticketingbackend.repository.airline;

import com.airlinesticketingbackend.entity.flight.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteEntityRepository extends JpaRepository<RouteEntity, Long> {
    RouteEntity findByRouteId(Long routeId);
}
