package com.airlinesticketingbackend.controller.flight;

import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.dto.flight.CreateFlightRequest;
import com.airlinesticketingbackend.dto.flight.CreateReservationRequest;
import com.airlinesticketingbackend.dto.flight.FlightReservationInfo;
import com.airlinesticketingbackend.dto.flight.PersistFlightResponse;
import com.airlinesticketingbackend.service.flight.FlightService;
import com.airlinesticketingbackend.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/flight")
public class FlightController {

    private final FlightService flightService;
    private final ReservationService reservationService;

    @Autowired
    public FlightController(FlightService flightService, ReservationService reservationService) {
        this.flightService = flightService;
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/persistFlight")
    public ResponseEntity<GenericResult<PersistFlightResponse>> persistFlight(@RequestBody CreateFlightRequest request) {
        GenericResult<PersistFlightResponse> response = this.flightService.createFlight(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/buyTicket")
    public ResponseEntity<GenericResult<CreateReservationRequest>> buyTicket(@RequestBody CreateReservationRequest request) {
        GenericResult<CreateReservationRequest> response = this.reservationService.buyTicket(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/cancelReservation/{pnrCode}")
    public ResponseEntity<ProcessResult> cancelReservation(@PathVariable String pnrCode) {
        ProcessResult result = this.flightService.cancelReservation(pnrCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/inquireReservationByTicketCode")
    public ResponseEntity<FlightReservationInfo> inquireReservationByTicketCode(@RequestParam String pnrCode) {
        FlightReservationInfo result = this.flightService.searchTicketByPnrCode(pnrCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
