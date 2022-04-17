package com.airlinesticketingbackend.repository.flight;

import com.airlinesticketingbackend.entity.flight.FlightReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightReservationEntityRepository extends JpaRepository<FlightReservationEntity, Long> {
    FlightReservationEntity findByPnrCode(String pnrCode);
}
