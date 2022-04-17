package com.airlinesticketingbackend.service.flight;

import com.airlinesticketingbackend.base.BusinessEnumerationTypes.*;
import com.airlinesticketingbackend.base.Constants;
import com.airlinesticketingbackend.base.exception.ProcessResultException;
import com.airlinesticketingbackend.base.exception.ProvideExceptionHandler;
import com.airlinesticketingbackend.base.mapper.DozerMapper;
import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.dto.flight.*;
import com.airlinesticketingbackend.entity.flight.FlightReservationEntity;
import com.airlinesticketingbackend.entity.payment.PaymentHistoryEntity;
import com.airlinesticketingbackend.repository.flight.FlightEntityRepository;
import com.airlinesticketingbackend.repository.flight.FlightReservationEntityRepository;
import com.airlinesticketingbackend.repository.payment.PaymentHistoryEntityRepository;
import com.airlinesticketingbackend.service.base.AbstractService;
import com.airlinesticketingbackend.service.base.UtilityService;
import com.airlinesticketingbackend.service.builder.CreateFlightBuilder;
import com.airlinesticketingbackend.service.builder.CreateFlightReservationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FlightServiceImpl extends AbstractService implements FlightService {

    private final CreateFlightBuilder flightBuilder;
    private final FlightEntityRepository flightEntityRepository;
    private final DozerMapper dozerMapper;
    private final FlightReservationEntityRepository flightReservationReservation;
    private final UtilityService utilityService;
    private final PaymentHistoryEntityRepository paymentHistoryRepository;

    @Autowired
    public FlightServiceImpl(CreateFlightBuilder flightBuilder, FlightEntityRepository flightEntityRepository, DozerMapper dozerMapper, FlightReservationEntityRepository flightReservationReservation, UtilityService utilityService, PaymentHistoryEntityRepository paymentHistoryRepository) {
        this.flightBuilder = flightBuilder;
        this.flightEntityRepository = flightEntityRepository;
        this.dozerMapper = dozerMapper;
        this.flightReservationReservation = flightReservationReservation;
        this.utilityService = utilityService;
        this.paymentHistoryRepository = paymentHistoryRepository;
    }

    @Override
    @Transactional
    public GenericResult<PersistFlightResponse> createFlight(CreateFlightRequest request) {
        return this.flightBuilder
                .initialize()
                .validate(request)
                .desiredRoute(request)
                .desiredAirlineCompany(request)
                .buildFlight(request);
    }

    @Override
    public Flight inquireFlightInfo(String flightNumber) {
        return Optional.ofNullable(this.flightEntityRepository.findByFlightNumber(flightNumber))
                .map(flight -> this.dozerMapper.map(flight, Flight.class, "FlightEntity_Flight"))
                .orElseThrow(() -> new ProvideExceptionHandler("FLIGHT_NOT_FOUND"));
    }

    @Override
    public ProcessResult cancelReservation(String pnrCode) {
        FlightReservationEntity reservation = this.flightReservationReservation.findByPnrCode(pnrCode);

        Long cancelReservationStId = this.utilityService.findGeneralStatusId(
                FlightReservationStatus.CANCELLED.name(),
                EntityCodeName.FLIGHT_RESERVATION.name(),
                Constants.TRUE_FLAG);

        reservation.setBookingStatusId(cancelReservationStId);

        PaymentHistoryEntity payment = this.paymentHistoryRepository.findByPnrCode(pnrCode);

        Long cancelPaymentSt = this.utilityService.findGeneralStatusId(
                PaymentStatus.CANCELLED.name(),
                EntityCodeName.PAYMENT_HISTORY.name(),
                Constants.TRUE_FLAG);

        payment.setPaymentStId(cancelPaymentSt);

        ProcessResult result = ProcessResult.success("Cancel Reservation API");
        try {
            this.paymentHistoryRepository.save(payment);
            this.flightReservationReservation.save(reservation);

            result.setMessage("Ticket cancellation occurred. ");
        } catch (ProvideExceptionHandler exc) {
            throw new ProcessResultException(
                    ProcessResult
                            .internalServerError("The operation for this pnr code could not be performed: " + pnrCode), true);
        }

        return result;
    }

    @Override
    public FlightReservationInfo searchTicketByPnrCode(String pnrCode) {
        return Optional.ofNullable(this.flightReservationReservation.findByPnrCode(pnrCode))
                .map(ticket -> this.dozerMapper.map(ticket, FlightReservationInfo.class, "FlightReservationEntity_FlightReservationInfo"))
                .orElseThrow(() -> new ProvideExceptionHandler("TICKET_NOT_FOUND"));
    }
}
