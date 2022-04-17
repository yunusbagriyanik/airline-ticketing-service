package com.airlinesticketingbackend.service.builder;

import com.airlinesticketingbackend.base.exception.ProcessResultException;
import com.airlinesticketingbackend.base.exception.ProvideExceptionHandler;
import com.airlinesticketingbackend.base.mapper.DozerMapper;
import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.dto.flight.CreateReservationRequest;
import com.airlinesticketingbackend.dto.flight.Flight;
import com.airlinesticketingbackend.entity.flight.FlightEntity;
import com.airlinesticketingbackend.entity.flight.FlightReservationEntity;
import com.airlinesticketingbackend.repository.flight.FlightEntityRepository;
import com.airlinesticketingbackend.repository.flight.FlightReservationEntityRepository;
import com.airlinesticketingbackend.service.flight.FlightService;
import com.airlinesticketingbackend.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public record CreateFlightReservationBuilder(FlightService flightService,
                                             DozerMapper dozerMapper,
                                             PaymentService paymentService,
                                             FlightReservationEntityRepository reservationRepository,
                                             FlightEntityRepository flightRepository) {

    @Autowired
    public CreateFlightReservationBuilder {
    }

    public CreateFlightReservationBuilder initialize() {
        return this;
    }

    public CreateFlightReservationBuilder before(CreateReservationRequest request) {
        this.validatePnrCode(request.getPnrCode());

        Flight flight = this.flightService.inquireFlightInfo(request.getFlightNumber());
        this.validateFullSeat(flight.getFlightQuote());
        return this;
    }

    public CreateFlightReservationBuilder completeOrder(CreateReservationRequest request) {
        Flight flight = this.flightService.inquireFlightInfo(request.getFlightNumber());
        this.updatePrice(request);
        try {
            FlightReservationEntity flightResEntity = this.dozerMapper.map(request, FlightReservationEntity.class, "CreateReservationRequest_FlightReservationEntity");
            flightResEntity.setFlightId(flight.getFlightId());
            this.reservationRepository.save(flightResEntity);
        } catch (ProcessResultException exc) {

        }
        return this;
    }

    private void updatePrice(CreateReservationRequest request) {
        FlightEntity flightEntity = this.flightRepository.findByFlightNumber(request.getFlightNumber());
        int flightQuote = flightEntity.getFlightQuote();
        int emptySeat = flightEntity.getEmptySeat();
        int fullSeat = flightEntity.getFullSeat();

        double percent = 100 * ((flightQuote - emptySeat) / (double) flightQuote);
        double percentResult = Math.abs(percent);
        double floor = Math.floor(percentResult);

        if (floor == 10) {
            // update fare ticket
            double amountPaid = request.getCreatePaymentRequest().getAmountPaid();
            amountPaid = request.getCreatePaymentRequest().getAmountPaid() + ((request.getCreatePaymentRequest().getAmountPaid() * 10) / 100);

            request.getCreatePaymentRequest().setAmountPaid(amountPaid);

            int newQuote = 0;
            newQuote = emptySeat;

            flightEntity.setFlightQuote(newQuote);
            flightEntity.setFare(amountPaid);
            flightEntity.setEmptySeat(emptySeat - 1);
            flightEntity.setFullSeat(fullSeat + 1);
            this.flightRepository.save(flightEntity);
        } else {
            flightEntity.setEmptySeat(emptySeat - 1);
            flightEntity.setFullSeat(fullSeat + 1);
        }
    }

    public CreateFlightReservationBuilder doPayment(CreateReservationRequest request) {
        this.paymentService.doPayment(request.getCreatePaymentRequest());
        return this;
    }

    public GenericResult<CreateReservationRequest> result(CreateReservationRequest request) {
        GenericResult<CreateReservationRequest> response = new GenericResult<CreateReservationRequest>();
        response.setResult(request);
        response.setProcessResult(ProcessResult.success("Buy Ticket Service"));

        return response;
    }

    private void validateFullSeat(int totalSeat) {
        if (totalSeat <= 0)
            throw new ProcessResultException(
                    ProcessResult.badRequest("There are no empty seats. " + "CreateFlightReservationBuilder.validateFullSeat"), false);
    }

    private void validatePnrCode(String pnrCode) {
        FlightReservationEntity flightReservation = this.reservationRepository.findByPnrCode(pnrCode);
        if (flightReservation != null)
            throw new ProvideExceptionHandler("There is a ticket for this pnr code. ");
    }

}
