package com.airlinesticketingbackend.service.airport.company;

import com.airlinesticketingbackend.dto.SearchRequest;
import com.airlinesticketingbackend.dto.airport.AirlineCompany;
import com.airlinesticketingbackend.dto.common.ProcessResult;

public interface IAirlineCompanyService {
    ProcessResult createAirlineCompany(AirlineCompany request);

    AirlineCompany searchAirlineCompany(SearchRequest request);
}
