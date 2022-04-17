package com.airlinesticketingbackend.dto.flight;

import com.airlinesticketingbackend.dto.payment.CreatePaymentRequest;

import java.util.Date;

public class CreateReservationRequest {
    private String flightNumber;
    private String pnrCode;
    private String email;
    private String phoneNumber;
    private String fname;
    private String lname;
    private String tckNo;
    private Date dateOfBirth;
    private CreatePaymentRequest createPaymentRequest;
    private Long bookingStatusId;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPnrCode() {
        return pnrCode;
    }

    public void setPnrCode(String pnrCode) {
        this.pnrCode = pnrCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getTckNo() {
        return tckNo;
    }

    public void setTckNo(String tckNo) {
        this.tckNo = tckNo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public CreatePaymentRequest getCreatePaymentRequest() {
        return createPaymentRequest;
    }

    public void setCreatePaymentRequest(CreatePaymentRequest createPaymentRequest) {
        this.createPaymentRequest = createPaymentRequest;
    }

    public Long getBookingStatusId() {
        return bookingStatusId;
    }

    public void setBookingStatusId(Long bookingStatusId) {
        this.bookingStatusId = bookingStatusId;
    }
}
