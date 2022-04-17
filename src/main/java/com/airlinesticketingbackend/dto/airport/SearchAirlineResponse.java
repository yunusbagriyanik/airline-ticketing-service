package com.airlinesticketingbackend.dto.airport;

import com.airlinesticketingbackend.dto.address.Address;

public class SearchAirlineResponse {
    private Long airlineId;
    private String airportName;
    private Address address;

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
