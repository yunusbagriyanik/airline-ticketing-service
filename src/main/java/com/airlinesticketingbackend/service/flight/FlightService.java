package com.airlinesticketingbackend.service.flight;

import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.dto.flight.*;

public interface FlightService {
    GenericResult<PersistFlightResponse> createFlight(CreateFlightRequest request);

    Flight inquireFlightInfo(String flightNumber);

    ProcessResult cancelReservation(String pnrCode);

    FlightReservationInfo searchTicketByPnrCode(String pnrCode);
}
