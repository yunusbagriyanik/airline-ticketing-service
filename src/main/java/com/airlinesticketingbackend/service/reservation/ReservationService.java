package com.airlinesticketingbackend.service.reservation;

import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.flight.CreateReservationRequest;

public interface ReservationService {
    GenericResult<CreateReservationRequest> buyTicket(CreateReservationRequest request);
}
