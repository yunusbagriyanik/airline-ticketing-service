package com.airlinesticketingbackend.service.payment;

import com.airlinesticketingbackend.base.BusinessEnumerationTypes;
import com.airlinesticketingbackend.base.Constants;
import com.airlinesticketingbackend.base.common.UtilityTool;
import com.airlinesticketingbackend.base.exception.ProcessResultException;
import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.dto.payment.CreatePaymentRequest;
import com.airlinesticketingbackend.entity.payment.PaymentHistoryEntity;
import com.airlinesticketingbackend.repository.payment.PaymentHistoryEntityRepository;
import com.airlinesticketingbackend.service.base.AbstractService;
import com.airlinesticketingbackend.service.base.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends AbstractService implements PaymentService {

    private final PaymentHistoryEntityRepository paymentHistoryRepository;
    private final UtilityService utilityService;

    @Autowired
    public PaymentServiceImpl(PaymentHistoryEntityRepository paymentHistoryRepository, UtilityService utilityService) {
        this.paymentHistoryRepository = paymentHistoryRepository;
        this.utilityService = utilityService;
    }

    @Override
    public ProcessResult doPayment(CreatePaymentRequest request) {
        PaymentHistoryEntity paymentHistoryEntity = new PaymentHistoryEntity();

        try {
            this.maskedCreditCardNumber(paymentHistoryEntity, request);

            paymentHistoryEntity.setCardOwner(request.getCardOwner());
            paymentHistoryEntity.setCvv(request.getCvv());
            paymentHistoryEntity.setDay(request.getDay());
            paymentHistoryEntity.setPnrCode(request.getPnrCode());
            paymentHistoryEntity.setMonth(request.getMonth());
            paymentHistoryEntity.setAmountPaid(request.getAmountPaid());

            Long paymentStId = this.utilityService.findGeneralStatusId(
                    BusinessEnumerationTypes.PaymentStatus.SUCCESS.name(),
                    BusinessEnumerationTypes.EntityCodeName.PAYMENT_HISTORY.name(),
                    Constants.TRUE_FLAG);

            paymentHistoryEntity.setPaymentStId(paymentStId);

            this.paymentHistoryRepository.save(paymentHistoryEntity);
        } catch (Exception e) {
            logger.error("PAYMENT_FAIL");
            throw new ProcessResultException(
                    ProcessResult
                            .internalServerError("PAYMENT_FAIL" + request.getCardOwner()), false);
        }

        return ProcessResult.success("Do Payment API");
    }

    private void maskedCreditCardNumber(PaymentHistoryEntity paymentHistoryEntity, CreatePaymentRequest request) {
        String cardNumber;
        cardNumber = UtilityTool.splintRedundantExpression(request.getCardNumber(), "-");
        String maskedNumber = UtilityTool.maskRequestParam(cardNumber, 6, 13, '*');
        paymentHistoryEntity.setCardNumber(maskedNumber);

        request.setCardNumber(maskedNumber);
    }
}
