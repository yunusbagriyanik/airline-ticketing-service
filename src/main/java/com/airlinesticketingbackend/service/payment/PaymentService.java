package com.airlinesticketingbackend.service.payment;

import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.dto.payment.CreatePaymentRequest;

public interface PaymentService {
    ProcessResult doPayment(CreatePaymentRequest request);
}
