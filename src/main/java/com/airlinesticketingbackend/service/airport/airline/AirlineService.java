package com.airlinesticketingbackend.service.airport.airline;

import com.airlinesticketingbackend.dto.SearchRequest;
import com.airlinesticketingbackend.dto.airport.CreateAirlineRequest;
import com.airlinesticketingbackend.dto.airport.Route;
import com.airlinesticketingbackend.dto.airport.SearchAirlineResponse;
import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.common.ProcessResult;

public interface AirlineService {
    GenericResult<CreateAirlineRequest> createAirport(CreateAirlineRequest request);

    SearchAirlineResponse createAirport(SearchRequest request);

    ProcessResult createRoute(Route request);

    Route inquireRouteByRouteId(Long routeId);
}
