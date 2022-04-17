package com.airlinesticketingbackend.entity.payment;

import com.airlinesticketingbackend.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payment_history")
public class PaymentHistoryEntity extends BaseEntity {
    private Long paymentHistoryId;
    private Long flightReservationId;
    private String cardOwner;
    private String cardNumber;
    private int month;
    private int day;
    private int cvv;
    private double amountPaid;
    private Long paymentStId;
    private String pnrCode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_history_id")
    public Long getPaymentHistoryId() {
        return paymentHistoryId;
    }

    public void setPaymentHistoryId(Long paymentHistoryId) {
        this.paymentHistoryId = paymentHistoryId;
    }

    @Basic
    @Column(name = "reservation_id")
    public Long getFlightReservationId() {
        return flightReservationId;
    }

    public void setFlightReservationId(Long flightReservationId) {
        this.flightReservationId = flightReservationId;
    }

    @Basic
    @Column(name = "cardOwner")
    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    @Basic
    @Column(name = "card_number")
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Basic
    @Column(name = "month")
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Basic
    @Column(name = "day")
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Basic
    @Column(name = "cvv")
    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @Basic
    @Column(name = "amount_paid")
    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Basic
    @Column(name = "payment_st_id")
    public Long getPaymentStId() {
        return paymentStId;
    }

    public void setPaymentStId(Long paymentStId) {
        this.paymentStId = paymentStId;
    }

    @Basic
    @Column(name = "pnr_code")
    public String getPnrCode() {
        return pnrCode;
    }

    public void setPnrCode(String pnrCode) {
        this.pnrCode = pnrCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentHistoryEntity that = (PaymentHistoryEntity) o;
        return month == that.month &&
                day == that.day &&
                cvv == that.cvv &&
                Double.compare(that.amountPaid, amountPaid) == 0 &&
                Objects.equals(paymentHistoryId, that.paymentHistoryId) &&
                Objects.equals(flightReservationId, that.flightReservationId) &&
                Objects.equals(cardOwner, that.cardOwner) &&
                Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(paymentStId, that.paymentStId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentHistoryId, flightReservationId, cardOwner, cardNumber, month, day, cvv, amountPaid, paymentStId);
    }
}
