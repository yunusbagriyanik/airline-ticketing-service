package com.airlinesticketingbackend.service.reservation;

import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.flight.CreateReservationRequest;
import com.airlinesticketingbackend.service.builder.CreateFlightReservationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final CreateFlightReservationBuilder flightReservationBuilder;

    @Autowired
    public ReservationServiceImpl(CreateFlightReservationBuilder flightReservationBuilder) {
        this.flightReservationBuilder = flightReservationBuilder;
    }

    @Override
    @Transactional
    public GenericResult<CreateReservationRequest> buyTicket(CreateReservationRequest request) {
        return this.flightReservationBuilder
                .initialize()
                .before(request)
                .completeOrder(request)
                .doPayment(request)
                .result(request);
    }
}
