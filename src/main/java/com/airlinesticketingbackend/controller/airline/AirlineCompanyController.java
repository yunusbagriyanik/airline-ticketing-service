package com.airlinesticketingbackend.controller.airline;

import com.airlinesticketingbackend.dto.SearchRequest;
import com.airlinesticketingbackend.dto.airport.AirlineCompany;
import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.service.airport.company.IAirlineCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/airlineCompany")
public class AirlineCompanyController {

    private final IAirlineCompanyService airlineCompanyService;

    @Autowired
    public AirlineCompanyController(IAirlineCompanyService airlineCompanyService) {
        this.airlineCompanyService = airlineCompanyService;
    }

    @PostMapping(value = "/createAirlineCompany")
    public ResponseEntity<ProcessResult> createAirlineCompany(@RequestBody AirlineCompany request) {
        ProcessResult response = this.airlineCompanyService.createAirlineCompany(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/searchAirlineCompany")
    public ResponseEntity<AirlineCompany> searchAirlineCompany(@RequestBody SearchRequest request) {
        AirlineCompany response = this.airlineCompanyService.searchAirlineCompany(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
