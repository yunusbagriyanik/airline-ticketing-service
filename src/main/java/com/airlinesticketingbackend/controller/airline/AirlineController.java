package com.airlinesticketingbackend.controller.airline;

import com.airlinesticketingbackend.dto.SearchRequest;
import com.airlinesticketingbackend.dto.airport.CreateAirlineRequest;
import com.airlinesticketingbackend.dto.airport.Route;
import com.airlinesticketingbackend.dto.airport.SearchAirlineResponse;
import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.service.airport.airline.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/airline")
public class AirlineController {

    private final AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @PostMapping(value = "/createAirport")
    public ResponseEntity<GenericResult<CreateAirlineRequest>> createAirport(@RequestBody CreateAirlineRequest request) {
        GenericResult<CreateAirlineRequest> response = this.airlineService.createAirport(request);
        return new ResponseEntity<>(response, response != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/searchAirport")
    public ResponseEntity<SearchAirlineResponse> searchAirport(@RequestBody SearchRequest request) {
        SearchAirlineResponse response = this.airlineService.createAirport(request);
        return new ResponseEntity<>(response, response != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/createRoute")
    public ResponseEntity<ProcessResult> createRoute(@RequestBody Route request) {
        ProcessResult response = this.airlineService.createRoute(request);
        return new ResponseEntity<>(response, response != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/inquireRouteByRouteId")
    public ResponseEntity<Route> inquireRouteByRouteId(@RequestParam Long routeId) {
        Route response = this.airlineService.inquireRouteByRouteId(routeId);
        return new ResponseEntity<>(response, response != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }


}
