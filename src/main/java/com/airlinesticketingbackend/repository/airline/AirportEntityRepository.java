package com.airlinesticketingbackend.repository.airline;

import com.airlinesticketingbackend.entity.airport.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportEntityRepository extends JpaRepository<AirportEntity, Long> {
    AirportEntity findByName(String name);
}
