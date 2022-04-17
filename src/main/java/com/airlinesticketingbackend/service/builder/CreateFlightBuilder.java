package com.airlinesticketingbackend.service.builder;

import com.airlinesticketingbackend.base.Constants;
import com.airlinesticketingbackend.base.exception.ProvideExceptionHandler;
import com.airlinesticketingbackend.base.mapper.DozerMapper;
import com.airlinesticketingbackend.dto.airport.Route;
import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.dto.flight.CreateFlightRequest;
import com.airlinesticketingbackend.dto.flight.Flight;
import com.airlinesticketingbackend.dto.flight.PersistFlightResponse;
import com.airlinesticketingbackend.dto.general.Status;
import com.airlinesticketingbackend.entity.flight.FlightEntity;
import com.airlinesticketingbackend.repository.airline.RouteEntityRepository;
import com.airlinesticketingbackend.repository.flight.FlightEntityRepository;
import com.airlinesticketingbackend.service.airport.airline.AirlineService;
import com.airlinesticketingbackend.service.base.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public record CreateFlightBuilder(RouteEntityRepository routeRepository,
                                  UtilityService utilityService,
                                  DozerMapper dozerMapper,
                                  FlightEntityRepository flightRepository,
                                  AirlineService airlineService) {

    @Autowired
    public CreateFlightBuilder {
    }

    public CreateFlightBuilder initialize() {
        return this;
    }

    public CreateFlightBuilder validate(CreateFlightRequest request) {
        if (request.getRouteId() == null)
            throw new ProvideExceptionHandler("ROUTE_ID_MUST_NOT_BE_NULL");
        if (request.getAirlineCompId() == null)
            throw new ProvideExceptionHandler("AIRLINE_COMPANY_ID_MUST_NOT_BE_NULL");
        if (request.getFlightNumber() == null)
            throw new ProvideExceptionHandler("FLIGHT_NUMBER_MUST_NOT_BE_NULL");
        if (request.getArrivalTime() == null)
            throw new ProvideExceptionHandler("ARRIVAL_TIME_MUST_NOT_BE_NULL");
        if (request.getDepartureTime() == null)
            throw new ProvideExceptionHandler("DEPARTURE_TIME_MUST_NOT_BE_NULL");
        if (request.getDepartureDate() == null)
            throw new ProvideExceptionHandler("DEPARTURE_DATE_MUST_NOT_BE_NULL");
        if (request.getArrivalDate() == null)
            throw new ProvideExceptionHandler("ARRIVAL_DATE_MUST_NOT_BE_NULL");

        this.validateFlightNumber(request.getFlightNumber());
        this.validateFlightDateAndTime(request.getDepartureDate(), request.getDepartureTime(), request.getRouteId());
        return this;
    }

    public CreateFlightBuilder desiredRoute(CreateFlightRequest request) {
        this.routeIsActive(request.getRouteId());
        return this;
    }

    public CreateFlightBuilder desiredAirlineCompany(CreateFlightRequest request) {
        return this;
    }

    private void routeIsActive(Long routeId) {
        Optional.ofNullable(this.routeRepository.findByRouteId(routeId))
                .filter(route -> route.getIsActv().equals(Constants.TRUE_FLAG))
                .orElseThrow(() -> new ProvideExceptionHandler("NO_SUCH_ROTE_WAS_FOUND"));
    }

    public GenericResult<PersistFlightResponse> buildFlight(CreateFlightRequest request) {
        GenericResult<PersistFlightResponse> response = new GenericResult<PersistFlightResponse>();

        Flight flight = Optional.of(this.dozerMapper.map(request, FlightEntity.class, "CreateFlightRequest_FlightEntity"))
                .map(this.flightRepository::save)
                .map(flightEntity -> this.dozerMapper.map(flightEntity, Flight.class, "FlightEntity_Flight"))
                .orElseThrow(() -> new ProvideExceptionHandler("CreateFlightBuilder.buildFlight.ERROR"));

        this.fillRouteAndFlightStatusInfo(request, flight);

        PersistFlightResponse persistResponse = new PersistFlightResponse();
        persistResponse.setFlight(flight);

        ProcessResult successResult = ProcessResult.success("Build Flight API");
        response.setResult(persistResponse);
        response.setProcessResult(successResult);

        return response;
    }

    private void fillRouteAndFlightStatusInfo(CreateFlightRequest request, Flight flight) {
        Status flightStatus = this.utilityService.findGeneralStatusById(request.getFlightStId());
        flight.setStatus(flightStatus);

        Route flightRoute = this.airlineService.inquireRouteByRouteId(request.getRouteId());
        flight.setRoute(flightRoute);
    }

    private void validateFlightDateAndTime(Date departureDate, String departureTime, Long routeId) {
        List<FlightEntity> flights = this.flightRepository.findByFlightDateInfo(departureDate).stream()
                .filter(fe -> fe.getFlightRouteId().equals(routeId))
                .filter(fe -> fe.getDepartureTime().equals(departureTime))
                .toList();

        if (!flights.isEmpty())
            throw new ProvideExceptionHandler("Two flights cannot be defined on the same day and time. ");
    }

    private void validateFlightNumber(String flightNumber) {
        FlightEntity flight = this.flightRepository.findByFlightNumber(flightNumber);
        if (flight != null)
            throw new ProvideExceptionHandler("More than one flight cannot be defined for the same flight number. ");
    }


}
