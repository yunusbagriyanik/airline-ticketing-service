package com.airlinesticketingbackend.repository.airline;

import com.airlinesticketingbackend.entity.airport.AirlineCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineCompanyEntityRepository extends JpaRepository<AirlineCompanyEntity, Long> {
    AirlineCompanyEntity findByName(String name);
}
