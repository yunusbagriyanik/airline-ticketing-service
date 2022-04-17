package com.airlinesticketingbackend.entity.flight;

import com.airlinesticketingbackend.base.Constants;
import com.airlinesticketingbackend.entity.base.BaseEntity;
import com.airlinesticketingbackend.entity.general.StatusEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "flight")
public class FlightEntity extends BaseEntity {
    private Long flightId;
    private String flightNumber;
    private Long flightRouteId;
    private Date departureDate;
    private String departureTime;
    private Date arrivalDate;
    private String arrivalTime;
    private Long flightStId;
    private StatusEntity statusEntity;
    private Integer isActv;
    private Long airlineCompId;
    private RouteEntity routeEntity;
    private int flightQuote;
    private double fare;
    private int emptySeat;
    private int fullSeat;
    private int staticQuote;
    private double startingFee;

    @PrePersist
    public void onPrePersist() {
        this.isActv = Constants.TRUE_FLAG;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    @Basic
    @Column(name = "flight_number")
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Basic
    @Column(name = "flight_route_id")
    public Long getFlightRouteId() {
        return flightRouteId;
    }

    public void setFlightRouteId(Long flightRouteId) {
        this.flightRouteId = flightRouteId;
    }

    @Basic
    @Column(name = "departure_date")
    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @Basic
    @Column(name = "departure_time")
    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @Basic
    @Column(name = "arrival_date")
    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Basic
    @Column(name = "arrival_time")
    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Basic
    @Column(name = "flight_st_id")
    public Long getFlightStId() {
        return flightStId;
    }

    public void setFlightStId(Long flightStId) {
        this.flightStId = flightStId;
    }

    @OneToOne(targetEntity = StatusEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_st_id", referencedColumnName = "gnl_st_id", insertable = false, updatable = false)
    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity gnlStEntity) {
        this.statusEntity = gnlStEntity;
    }

    @Basic
    @Column(name = "is_actv")
    public Integer getIsActv() {
        return isActv;
    }

    public void setIsActv(Integer isActv) {
        this.isActv = isActv;
    }

    @Basic
    @Column(name = "airline_comp_id")
    public Long getAirlineCompId() {
        return airlineCompId;
    }

    public void setAirlineCompId(Long airlineCompId) {
        this.airlineCompId = airlineCompId;
    }

    @OneToOne(targetEntity = RouteEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_route_id", referencedColumnName = "route_id", insertable = false, updatable = false)
    public RouteEntity getRouteEntity() {
        return routeEntity;
    }

    public void setRouteEntity(RouteEntity routeEntity) {
        this.routeEntity = routeEntity;
    }

    @Basic
    @Column(name = "flight_quote")
    public int getFlightQuote() {
        return flightQuote;
    }

    public void setFlightQuote(int flightQuote) {
        this.flightQuote = flightQuote;
    }

    @Basic
    @Column(name = "fare")
    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    @Basic
    @Column(name = "empty_seat")
    public int getEmptySeat() {
        return emptySeat;
    }

    public void setEmptySeat(int emptySeat) {
        this.emptySeat = emptySeat;
    }

    @Basic
    @Column(name = "full_seat")
    public int getFullSeat() {
        return fullSeat;
    }

    public void setFullSeat(int fullSeat) {
        this.fullSeat = fullSeat;
    }

    @Basic
    @Column(name = "static_quote")
    public int getStaticQuote() {
        return staticQuote;
    }

    public void setStaticQuote(int staticQuote) {
        this.staticQuote = staticQuote;
    }

    @Basic
    @Column(name = "starting_fee")
    public double getStartingFee() {
        return startingFee;
    }

    public void setStartingFee(double startingFee) {
        this.startingFee = startingFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightEntity that = (FlightEntity) o;
        return flightQuote == that.flightQuote &&
                Double.compare(that.fare, fare) == 0 &&
                emptySeat == that.emptySeat &&
                fullSeat == that.fullSeat &&
                staticQuote == that.staticQuote &&
                Double.compare(that.startingFee, startingFee) == 0 &&
                Objects.equals(flightId, that.flightId) &&
                Objects.equals(flightNumber, that.flightNumber) &&
                Objects.equals(flightRouteId, that.flightRouteId) &&
                Objects.equals(departureDate, that.departureDate) &&
                Objects.equals(departureTime, that.departureTime) &&
                Objects.equals(arrivalDate, that.arrivalDate) &&
                Objects.equals(arrivalTime, that.arrivalTime) &&
                Objects.equals(flightStId, that.flightStId) &&
                Objects.equals(statusEntity, that.statusEntity) &&
                Objects.equals(isActv, that.isActv) &&
                Objects.equals(airlineCompId, that.airlineCompId) &&
                Objects.equals(routeEntity, that.routeEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, flightNumber, flightRouteId, departureDate, departureTime, arrivalDate, arrivalTime, flightStId, statusEntity, isActv, airlineCompId, routeEntity, flightQuote, fare, emptySeat, fullSeat, staticQuote, startingFee);
    }
}
