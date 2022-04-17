package com.airlinesticketingbackend.entity.flight;

import com.airlinesticketingbackend.entity.base.BaseEntity;
import com.airlinesticketingbackend.entity.general.StatusEntity;
import com.airlinesticketingbackend.entity.payment.PaymentHistoryEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reservation")
public class FlightReservationEntity extends BaseEntity {
    private Long flightReservationId;
    private String pnrCode;
    private Long flightId;
    private String flightNumber;
    private String email;
    private String phoneNumber;
    private String fname;
    private String lname;
    private String tckNo;
    private Date dateOfBirth;
    private Long paymentId;
    private Long bookingStatusId;
    private StatusEntity statusEntity;
    private FlightEntity flightEntity;
    private PaymentHistoryEntity paymentHistoryEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    public Long getFlightReservationId() {
        return flightReservationId;
    }

    public void setFlightReservationId(Long flightReservationId) {
        this.flightReservationId = flightReservationId;
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
    @Column(name = "pnr_code")
    public String getPnrCode() {
        return pnrCode;
    }

    public void setPnrCode(String pnrCode) {
        this.pnrCode = pnrCode;
    }

    @Basic
    @Column(name = "reservation_flight_id")
    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "fname")
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Basic
    @Column(name = "lname")
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Basic
    @Column(name = "tck_no")
    public String getTckNo() {
        return tckNo;
    }

    public void setTckNo(String tckNo) {
        this.tckNo = tckNo;
    }

    @Basic
    @Column(name = "birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = "payment_id")
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "booking_st_id")
    public Long getBookingStatusId() {
        return bookingStatusId;
    }

    public void setBookingStatusId(Long bookingStatusId) {
        this.bookingStatusId = bookingStatusId;
    }


    @OneToOne(targetEntity = StatusEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_st_id", referencedColumnName = "gnl_st_id", insertable = false, updatable = false)
    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity gnlStEntity) {
        this.statusEntity = gnlStEntity;
    }

    @OneToOne(targetEntity = FlightEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_flight_id", referencedColumnName = "flight_id", insertable = false, updatable = false)
    public FlightEntity getFlightEntity() {
        return flightEntity;
    }

    public void setFlightEntity(FlightEntity flightEntity) {
        this.flightEntity = flightEntity;
    }

    @OneToOne(targetEntity = PaymentHistoryEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_history_id", insertable = false, updatable = false)
    public PaymentHistoryEntity getPaymentHistoryEntity() {
        return paymentHistoryEntity;
    }

    public void setPaymentHistoryEntity(PaymentHistoryEntity paymentHistoryEntity) {
        this.paymentHistoryEntity = paymentHistoryEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightReservationEntity that = (FlightReservationEntity) o;
        return Objects.equals(flightReservationId, that.flightReservationId) &&
                Objects.equals(flightId, that.flightId) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(fname, that.fname) &&
                Objects.equals(lname, that.lname) &&
                Objects.equals(tckNo, that.tckNo) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(bookingStatusId, that.bookingStatusId) &&
                Objects.equals(statusEntity, that.statusEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightReservationId, flightId, email, phoneNumber, fname, lname, tckNo, dateOfBirth, bookingStatusId, statusEntity);
    }
}
