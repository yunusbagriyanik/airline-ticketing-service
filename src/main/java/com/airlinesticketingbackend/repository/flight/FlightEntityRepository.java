package com.airlinesticketingbackend.repository.flight;

import com.airlinesticketingbackend.entity.flight.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FlightEntityRepository extends JpaRepository<FlightEntity, Long> {

    FlightEntity findByFlightNumber(String flightNumber);

    @Query("select fe from FlightEntity fe " +
            "where fe.departureDate = ?1")
    List<FlightEntity> findByFlightDateInfo(Date departureDate);
}
