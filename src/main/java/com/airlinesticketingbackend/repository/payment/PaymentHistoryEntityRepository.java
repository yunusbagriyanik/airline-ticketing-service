package com.airlinesticketingbackend.repository.payment;

import com.airlinesticketingbackend.entity.payment.PaymentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryEntityRepository extends JpaRepository<PaymentHistoryEntity, Long> {
    PaymentHistoryEntity findByPnrCode(String pnrCode);
}
