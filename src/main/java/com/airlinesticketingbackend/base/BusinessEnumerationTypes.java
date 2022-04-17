package com.airlinesticketingbackend.base;

public class BusinessEnumerationTypes {

    public enum AuthenticationOperation {
        USER_NOT_FOUND
    }

    public enum UserEnabledStatus {
        CONFIRM("confirm"),
        ACTV("active");

        private String shrtCode;

        UserEnabledStatus(String shrtCode) {
            this.shrtCode = shrtCode;
        }

        public String getShrtCode() {
            return shrtCode;
        }
    }

    public enum EntityCodeName {
        USER,
        PAYMENT_HISTORY,
        FLIGHT_RESERVATION
    }

    public enum FlightStatus {
        PREPARE_TAKE_OFF,
        WILL_BE_DELAYED,
        FLIGHT_COMPLETED,
        CANCELLED
    }

    public enum FlightReservationStatus {
        RESERVED,
        CANCELLED,
        RETURN_PROCESS,
        RETURN_MADE,
        PAYMENT_NOT_CONFIRMED
    }

    public enum PaymentStatus {
        PENDING,
        SUCCESS,
        COMPLETE,
        CANCELLED,
        REJECTED
    }

}
