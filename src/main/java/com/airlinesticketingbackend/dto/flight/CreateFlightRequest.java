package com.airlinesticketingbackend.dto.flight;

import java.util.Date;

public class CreateFlightRequest {
    private String flightNumber;
    private Long routeId;
    private Date departureDate;
    private String departureTime;
    private Date arrivalDate;
    private String arrivalTime;
    private Long flightStId;
    private Integer isActv;
    private Long airlineCompId;
    private int flightQuote;
    private double fare;
    private int emptySeat;
    private int fullSeat;
    private int staticQuote;
    private double startingFee;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getFlightStId() {
        return flightStId;
    }

    public void setFlightStId(Long flightStId) {
        this.flightStId = flightStId;
    }

    public Integer getIsActv() {
        return isActv;
    }

    public void setIsActv(Integer isActv) {
        this.isActv = isActv;
    }

    public Long getAirlineCompId() {
        return airlineCompId;
    }

    public void setAirlineCompId(Long airlineCompId) {
        this.airlineCompId = airlineCompId;
    }

    public int getFlightQuote() {
        return flightQuote;
    }

    public void setFlightQuote(int flightQuote) {
        this.flightQuote = flightQuote;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public int getEmptySeat() {
        return emptySeat;
    }

    public void setEmptySeat(int emptySeat) {
        this.emptySeat = emptySeat;
    }

    public int getFullSeat() {
        return fullSeat;
    }

    public void setFullSeat(int fullSeat) {
        this.fullSeat = fullSeat;
    }

    public int getStaticQuote() {
        return staticQuote;
    }

    public void setStaticQuote(int staticQuote) {
        this.staticQuote = staticQuote;
    }

    public double getStartingFee() {
        return startingFee;
    }

    public void setStartingFee(double startingFee) {
        this.startingFee = startingFee;
    }
}
